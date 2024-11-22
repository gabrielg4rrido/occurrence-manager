package com.gg.occurrence_manager.service;

import com.gg.occurrence_manager.exception.CustomBadRequestException;
import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.Endereco;
import com.gg.occurrence_manager.model.Ocorrencia;
import com.gg.occurrence_manager.model.dto.CadastroOcorrenciaDTO;
import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.model.enums.StatusOcorrencia;
import com.gg.occurrence_manager.repository.ClienteRepository;
import com.gg.occurrence_manager.repository.EnderecoRepository;
import com.gg.occurrence_manager.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public OcorrenciaDTO cadastrarOcorrencia(CadastroOcorrenciaDTO cadastroDTO) {
        Endereco endereco = enderecoRepository
                .findByCepAndLogradouroAndBairroAndCidadeAndEstado(
                        cadastroDTO.endereco().cep(),
                        cadastroDTO.endereco().logradouro(),
                        cadastroDTO.endereco().bairro(),
                        cadastroDTO.endereco().cidade(),
                        cadastroDTO.endereco().estado())
                .orElseGet(() -> enderecoRepository.save(new Endereco(cadastroDTO.endereco())));

        Cliente cliente = clienteRepository.findByNomeAndCpf(cadastroDTO.cliente().nome(), cadastroDTO.cliente().cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        //List<String> paths = minioService.uploadFiles(cadastroDTO.evidencias());

        Ocorrencia ocorrencia = new Ocorrencia(
                endereco,
                cliente,
                cadastroDTO.dataOcorrencia()
        );

        Ocorrencia ocorrenciaSalva = ocorrenciaRepository.save(ocorrencia);

        //paths.forEach(path -> ocorrencia.addFoto(new FotoOcorrencia(path)));

        return new OcorrenciaDTO(ocorrenciaSalva);
    }

    public Page<OcorrenciaDTO> listarOcorrencias(PageRequest pageRequest) {
        return ocorrenciaRepository.findAll(pageRequest)
                .map(OcorrenciaDTO::new);
    }

    public OcorrenciaDTO obterOcorrencia(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
        return new OcorrenciaDTO(ocorrencia);
    }

    public OcorrenciaDTO atualizarOcorrencia(Long id, OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if (StatusOcorrencia.FINALIZADA.equals(ocorrencia.getStatusOcorrencia())) {
            throw new CustomBadRequestException("Ocorrência não pode ser alterada pois já foi finalizada.");
        }

        ocorrencia.atualizar(ocorrenciaDTO);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        return new OcorrenciaDTO(ocorrencia);
    }

    public OcorrenciaDTO finalizarOcorrencia(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
        ocorrencia.setStatusOcorrencia(StatusOcorrencia.FINALIZADA);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);
        return new OcorrenciaDTO(ocorrencia);
    }

    public void deletarOcorrencia(Long id) {
        ocorrenciaRepository.deleteById(id);
    }
}