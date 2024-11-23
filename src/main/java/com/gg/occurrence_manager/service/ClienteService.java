package com.gg.occurrence_manager.service;

import com.gg.occurrence_manager.exception.CustomBadRequestException;
import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.dto.ClienteDTO;
import com.gg.occurrence_manager.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    public Page<ClienteDTO> listarClientes(PageRequest pageRequest) {
        return clienteRepository.findAll(pageRequest)
                .map(ClienteDTO::new);
    }

    public ClienteDTO obterCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.atualizar(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}