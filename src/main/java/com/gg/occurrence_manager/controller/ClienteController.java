package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.dto.ClienteDTO;
import com.gg.occurrence_manager.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Lista todos os clientes")
    public ResponseEntity<Page<ClienteDTO>> listarClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ClienteDTO> clientes = clienteService.listarClientes(pageRequest);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um cliente")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obterCliente(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    @Operation(summary = "Cadastra um cliente no sistema")
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriado = clienteService.criarCliente(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCriado.codigo()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um cliente no sistema")
    public ResponseEntity<ClienteDTO> atualizaarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok().body(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário do sistema")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
