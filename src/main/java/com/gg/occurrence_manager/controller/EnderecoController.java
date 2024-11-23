package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.dto.EnderecoDTO;
import com.gg.occurrence_manager.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/endereco")
@SecurityRequirement(name = "bearerAuth")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @Operation(summary = "Lista todos os endereços")
    public ResponseEntity<Page<EnderecoDTO>> listarEnderecos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EnderecoDTO> enderecos = enderecoService.listarEnderecos(pageRequest);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um endereço")
    public ResponseEntity<EnderecoDTO> obterEndereco(@PathVariable Long id) {
        EnderecoDTO endereco = enderecoService.obterEndereco(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    @Operation(summary = "Cadastra um endereço no sistema")
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoCriado = enderecoService.criarEndereco(enderecoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoCriado.codigo()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço no sistema")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDTO);
        return ResponseEntity.ok().body(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um endereço do sistema")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}