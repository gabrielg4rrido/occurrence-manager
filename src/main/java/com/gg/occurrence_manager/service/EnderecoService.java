package com.gg.occurrence_manager.service;

import com.gg.occurrence_manager.model.Endereco;
import com.gg.occurrence_manager.model.dto.EnderecoDTO;
import com.gg.occurrence_manager.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    public Page<EnderecoDTO> listarEnderecos(PageRequest pageRequest) {
        return enderecoRepository.findAll(pageRequest)
                .map(EnderecoDTO::new);
    }

    public EnderecoDTO obterEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return new EnderecoDTO(endereco);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.atualizar(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}