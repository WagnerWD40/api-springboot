package com.example.osworksapi.service;

import java.time.LocalDateTime;

import com.example.osworksapi.Model.Cliente;
import com.example.osworksapi.Model.OrdemServico;
import com.example.osworksapi.Model.StatusOrdemServico;
import com.example.osworksapi.exception.NegocioException;
import com.example.osworksapi.repository.ClienteRepository;
import com.example.osworksapi.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                                        .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);                                    
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

}