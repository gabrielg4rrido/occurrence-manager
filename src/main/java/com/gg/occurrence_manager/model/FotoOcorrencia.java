package com.gg.occurrence_manager.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
public class FotoOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_foto_ocorrencia")
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_ocorrencia")
    private Ocorrencia ocorrencia;

    @Column(name = "dta_criacao")
    private LocalDate dataCriacao;

    @Column(name = "dsc_path_bucket")
    private String pathBucket;

    @Column(name = "dsc_hash")
    private String hash;


}
