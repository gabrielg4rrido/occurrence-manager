package com.gg.occurrence_manager.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long codigo;

    @Column(name = "nme_cliente")
    private String nome;

    @Column(name = "dta_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nro_cpf", unique = true)
    private String cpf;

    @Column(name = "dta_criacao" )
    private LocalDate dataCriacao;
}
