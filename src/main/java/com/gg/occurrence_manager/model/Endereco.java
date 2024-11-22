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

    @Column(name = "nme_logradouro", nullable = false)
    @NotNull
    private String logradouro;

    @Column(name = "nme_bairro", nullable = false)
    @NotNull
    private String bairro;

    @Column(name = "nro_cep", nullable = false)
    @NotNull
    private String cep;

    @Column(name = "nme_cidade", nullable = false)
    @NotNull
    private String cidade;

    @Column(name = "nme_estado", nullable = false)
    @NotNull
    private String estado;

}
