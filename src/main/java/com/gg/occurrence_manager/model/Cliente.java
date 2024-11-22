package com.gg.occurrence_manager.model;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codigo;

    @Column(name = "nme_cliente", nullable = false)
    @NotNull
    private String nome;

    @Column(name = "dta_nascimento", nullable = false)
    @NotNull
    private LocalDate dataNascimento;

    @Column(name = "nro_cpf", nullable = false, unique = true)
    @NotNull
    private String cpf;

    @Column(name = "dta_criacao" )
    @CreationTimestamp
    private LocalDate dataCriacao;
}
