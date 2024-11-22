package com.gg.occurrence_manager.model;

import com.gg.occurrence_manager.model.dto.EnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
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

    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

    public void atualizar(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }
}
