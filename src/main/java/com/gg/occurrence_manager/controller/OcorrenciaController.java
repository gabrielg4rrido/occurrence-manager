package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.dto.CadastroOcorrenciaDTO;
import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> criarOcorrencia(@RequestBody CadastroOcorrenciaDTO cadastroOcorrenciaDTO) {
        OcorrenciaDTO ocorrenciaCriada = ocorrenciaService.cadastrarOcorrencia(cadastroOcorrenciaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaCriada);
    }

    @GetMapping
    public ResponseEntity<Page<OcorrenciaDTO>> listarOcorrencias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OcorrenciaDTO> ocorrencias = ocorrenciaService.listarOcorrencias(pageRequest);
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> obterOcorrencia(@PathVariable Long id) {
        OcorrenciaDTO ocorrencia = ocorrenciaService.obterOcorrencia(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> atualizarOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaDTO ocorrenciaDTO) {
        OcorrenciaDTO ocorrenciaAtualizada = ocorrenciaService.atualizarOcorrencia(id, ocorrenciaDTO);
        return ResponseEntity.ok(ocorrenciaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable Long id) {
        ocorrenciaService.deletarOcorrencia(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OcorrenciaDTO> finalizarOcorrencia(@PathVariable Long id) {
        OcorrenciaDTO ocorrenciaFinalizada = ocorrenciaService.finalizarOcorrencia(id);
        return ResponseEntity.ok(ocorrenciaFinalizada);
    }
}