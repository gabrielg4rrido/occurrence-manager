package com.gg.occurrence_manager.service;

import com.gg.occurrence_manager.exception.CustomBadRequestException;
import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.dto.ClienteDTO;
import com.gg.occurrence_manager.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = new Cliente(clienteDTO);
            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException("Erro: Dados inválidos ou em conflito. Verifique os campos enviados.");
        } catch (IllegalArgumentException ex) {
            throw new CustomBadRequestException("Erro: Argumento inválido fornecido.");
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado ao criar o cliente.");
        }
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
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