package com.example.osworksapi.Model;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdemServico ordemServico;
    
    private String descricao;
    private OffsetDateTime dataEnvio;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return this.ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataEnvio() {
        return this.dataEnvio;
    }

    public void setDataEnvio(OffsetDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comentario)) {
            return false;
        }
        Comentario comentario = (Comentario) o;
        return Objects.equals(id, comentario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}