package com.example.osworksapi.controller;

import javax.validation.Valid;

import com.example.osworksapi.Model.OrdemServico;
import com.example.osworksapi.service.GestaoOrdemServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    
    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return gestaoOrdemServicoService.criar(ordemServico);
    }

}