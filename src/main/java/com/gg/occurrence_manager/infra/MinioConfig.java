package com.gg.occurrence_manager.infra;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.SetBucketPolicyArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.minio.MinioClient;

@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
       try {
           MinioClient minioClient = MinioClient.builder()
                   .endpoint(url)
                   .credentials(accessKey, secretKey)
                   .build();

           String bucketName = "occ";

           boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
           if (!bucketExists) {
               minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
               System.out.println("Bucket '" + bucketName + "' criado com sucesso.");
           } else {
               System.out.println("Bucket '" + bucketName + "' já existe.");
           }

           String policyJson = "{"
                   + "  \"Version\": \"2012-10-17\","
                   + "  \"Statement\": ["
                   + "    {"
                   + "      \"Effect\": \"Allow\","
                   + "      \"Principal\": \"*\","
                   + "      \"Action\": ["
                   + "        \"s3:GetObject\""
                   + "      ],"
                   + "      \"Resource\": ["
                   + "        \"arn:aws:s3:::" + bucketName + "/*\""
                   + "      ]"
                   + "    }"
                   + "  ]"
                   + "}";

           minioClient.setBucketPolicy(
                   SetBucketPolicyArgs.builder()
                           .bucket(bucketName)
                           .config(policyJson)
                           .build()
           );

           return minioClient;
       } catch (Exception e) {
           throw new RuntimeException("Erro ao criar cliente MinIO: " + e.getMessage(), e);
       }
    }
}