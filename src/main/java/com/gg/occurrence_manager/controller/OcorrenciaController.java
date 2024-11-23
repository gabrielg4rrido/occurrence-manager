package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.dto.CadastroOcorrenciaDTO;
import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.service.OcorrenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
@SecurityRequirement(name = "bearerAuth")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping
    @Operation(summary = "Lista todos as ocorrências")
    public ResponseEntity<Page<OcorrenciaDTO>> listarOcorrencias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) LocalDate dataOcorrencia,
            @RequestParam(required = false) String cidade,
            @RequestParam(defaultValue = "dataOcorrencia,asc") String sort) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        Page<OcorrenciaDTO> ocorrencias = ocorrenciaService.listarOcorrencias(
                nomeCliente, cpf, dataOcorrencia, cidade, pageRequest);
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma ocorrência")
    public ResponseEntity<OcorrenciaDTO> obterOcorrencia(@PathVariable Long id) {
        OcorrenciaDTO ocorrencia = ocorrenciaService.obterOcorrencia(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Cadastra uma ocorrência no sistema")
    public ResponseEntity<OcorrenciaDTO> criarOcorrencia(
            @RequestPart("dados") @Valid CadastroOcorrenciaDTO cadastroOcorrenciaDTO,
            @RequestPart("evidencias") List<MultipartFile> evidencias) {
        OcorrenciaDTO ocorrenciaCriada = ocorrenciaService.cadastrarOcorrencia(cadastroOcorrenciaDTO, evidencias);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaCriada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma ocorrência no sistema")
    public ResponseEntity<OcorrenciaDTO> atualizarOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaDTO ocorrenciaDTO) {
        OcorrenciaDTO ocorrenciaAtualizada = ocorrenciaService.atualizarOcorrencia(id, ocorrenciaDTO);
        return ResponseEntity.ok(ocorrenciaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ocorrência do sistema")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable Long id) {
        ocorrenciaService.deletarOcorrencia(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/finalizar")
    @Operation(summary = "Finaliza uma ocorrência no sistema")
    public ResponseEntity<OcorrenciaDTO> finalizarOcorrencia(@PathVariable Long id) {
        OcorrenciaDTO ocorrenciaFinalizada = ocorrenciaService.finalizarOcorrencia(id);
        return ResponseEntity.ok(ocorrenciaFinalizada);
    }

    private Sort.Order parseSort(String sortParam) {
        String[] parts = sortParam.split(",");
        String property = parts[0];
        Sort.Direction direction = (parts.length > 1 && "desc".equalsIgnoreCase(parts[1]))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return new Sort.Order(direction, property);
    }
}