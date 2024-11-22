package com.gg.occurrence_manager.service;

import com.gg.occurrence_manager.exception.CustomBadRequestException;
import com.gg.occurrence_manager.model.Cliente;
import com.gg.occurrence_manager.model.Endereco;
import com.gg.occurrence_manager.model.FotoOcorrencia;
import com.gg.occurrence_manager.model.Ocorrencia;
import com.gg.occurrence_manager.model.dto.CadastroOcorrenciaDTO;
import com.gg.occurrence_manager.model.dto.OcorrenciaDTO;
import com.gg.occurrence_manager.model.enums.StatusOcorrencia;
import com.gg.occurrence_manager.repository.ClienteRepository;
import com.gg.occurrence_manager.repository.EnderecoRepository;
import com.gg.occurrence_manager.repository.FotoOcorrenciaRepository;
import com.gg.occurrence_manager.repository.OcorrenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FotoOcorrenciaRepository fotoOcorrenciaRepository;

    @Autowired
    private MinioService minioService;

    @Transactional
    public OcorrenciaDTO cadastrarOcorrencia(CadastroOcorrenciaDTO cadastroDTO, List<MultipartFile> evidencias) {
        Endereco endereco = enderecoRepository
                .findByCepAndLogradouroAndBairroAndCidadeAndEstado(
                        cadastroDTO.endereco().cep(),
                        cadastroDTO.endereco().logradouro(),
                        cadastroDTO.endereco().bairro(),
                        cadastroDTO.endereco().cidade(),
                        cadastroDTO.endereco().estado())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        Cliente cliente = clienteRepository.findByNomeAndCpf(cadastroDTO.cliente().nome(), cadastroDTO.cliente().cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        List<String> paths = minioService.uploadFiles(evidencias);

        Ocorrencia ocorrencia = new Ocorrencia(
                endereco,
                cliente,
                cadastroDTO.dataOcorrencia()
        );

        Ocorrencia ocorrenciaSalva = ocorrenciaRepository.save(ocorrencia);

        paths.forEach(path -> {
            String hash = gerarHashParaArquivo(path);
            FotoOcorrencia foto = new FotoOcorrencia(ocorrenciaSalva, path, hash);
            fotoOcorrenciaRepository.save(foto);
        });

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

    private String gerarHashParaArquivo(String filePath) {
        return UUID.randomUUID().toString();
    }
}