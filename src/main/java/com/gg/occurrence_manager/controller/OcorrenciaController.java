package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.dto.CadastroOcorrenciaDTO;
import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<OcorrenciaDTO>> listarOcorrencias() {
        List<OcorrenciaDTO> ocorrencias = ocorrenciaService.listarOcorrencias();
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
}