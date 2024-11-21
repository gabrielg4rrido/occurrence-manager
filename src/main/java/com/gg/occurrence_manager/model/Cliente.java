package com.gg.occurrence_manager.model;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codigo;

    @Column(name = "nme_cliente")
    @NotNull
    private String nome;

    @Column(name = "dta_nascimento")
    @NotNull
    private LocalDate dataNascimento;

    @Column(name = "nro_cpf", unique = true)
    @NotNull
    private String cpf;

    @Column(name = "dta_criacao" )
    private LocalDate dataCriacao;
}
