package com.gg.occurrence_manager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long codigo;

    @Column(name = "nme_logradouro")
    private String logradouro;

    @Column(name = "nme_bairro")
    private String bairro;

    @Column(name = "nro_cep")
    private String cep;

    @Column(name = "nme_cidade")
    private String cidade;

    @Column(name = "nme_estado")
    private String estado;

}
