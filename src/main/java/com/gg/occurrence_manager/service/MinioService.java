package com.gg.occurrence_manager.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> publicUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            try {
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());

                String publicUrl = String.format("%s/%s/%s", minioUrl, bucketName, fileName);
                publicUrls.add(publicUrl);

            } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
                throw new RuntimeException("Erro ao fazer upload para o MinIO: " + e.getMessage(), e);
            }
        }
        return publicUrls;
    }
}
