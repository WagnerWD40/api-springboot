package com.example.osworksapi.service;

import java.time.OffsetDateTime;

import com.example.osworksapi.Model.Cliente;
import com.example.osworksapi.Model.Comentario;
import com.example.osworksapi.Model.OrdemServico;
import com.example.osworksapi.Model.StatusOrdemServico;
import com.example.osworksapi.exception.EntidadeNaoEncontradaException;
import com.example.osworksapi.exception.NegocioException;
import com.example.osworksapi.repository.ClienteRepository;
import com.example.osworksapi.repository.ComentarioRepository;
import com.example.osworksapi.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                                        .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);                                    
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    private OrdemServico buscar(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Servico não encontrada"));
    }

}