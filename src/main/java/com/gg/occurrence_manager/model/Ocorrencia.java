package com.gg.occurrence_manager.model;

import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.model.enums.StatusOcorrencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ocorrencia")
@Data
@NoArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ocorrencia")
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_endereco", nullable = false)
    @NotNull
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "cod_cliente", nullable = false)
    @NotNull
    private Cliente cliente;

    @Column(name = "dta_ocorrencia", nullable = false)
    @NotNull
    private LocalDate dataOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "sta_ocorrencia")
    private StatusOcorrencia statusOcorrencia;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoOcorrencia> fotos = new ArrayList<>();

    public Ocorrencia(Endereco endereco, Cliente cliente, LocalDate dataOcorrencia) {
        this.endereco = endereco;
        this.cliente = cliente;
        this.dataOcorrencia = dataOcorrencia;
        this.statusOcorrencia = StatusOcorrencia.ABERTA;
    }

    public void atualizar(OcorrenciaDTO dto) {
        this.dataOcorrencia = dto.dataOcorrencia();
        this.statusOcorrencia = StatusOcorrencia.ABERTA;
    }
}
