package com.gg.occurrence_manager.controller;

import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.dto.ClienteDTO;
import com.gg.occurrence_manager.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriado = clienteService.criarCliente(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCriado.codigo()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obterCliente(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarEndereco(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok().body(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
