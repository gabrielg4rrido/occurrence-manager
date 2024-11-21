package com.gg.occurrence_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ocorrencia")
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_endereco")
    @NotNull
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    @NotNull
    private Cliente cliente;

    @Column(name = "dta_ocorrencia")
    @NotNull
    private LocalDate dataOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "sta_ocorrencia")
    private StatusOcorrencia statusOcorrencia;

}
