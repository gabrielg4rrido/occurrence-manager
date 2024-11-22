package com.gg.occurrence_manager.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "fotoocorrencia")
@Data
@NoArgsConstructor
public class FotoOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_foto_ocorrencia")
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_ocorrencia", nullable = false)
    @NotNull
    private Ocorrencia ocorrencia;

    @Column(name = "dta_criacao", nullable = false)
    @NotNull
    private LocalDate dataCriacao;

    @Column(name = "dsc_path_bucket")
    private String pathBucket;

    @Column(name = "dsc_hash")
    private String hash;

    public FotoOcorrencia(Ocorrencia ocorrencia, String pathBucket, String hash) {
        this.ocorrencia = ocorrencia;
        this.dataCriacao = LocalDate.now();
        this.pathBucket = pathBucket;
        this.hash = hash;
    }
}
