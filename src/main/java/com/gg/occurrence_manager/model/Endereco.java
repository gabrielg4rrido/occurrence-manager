package com.gg.occurrence_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long codigo;

    @Column(name = "nme_logradouro")
    @NotNull
    private String logradouro;

    @Column(name = "nme_bairro")
    @NotNull
    private String bairro;

    @Column(name = "nro_cep")
    @NotNull
    private String cep;

    @Column(name = "nme_cidade")
    @NotNull
    private String cidade;

    @Column(name = "nme_estado")
    @NotNull
    private String estado;

}
