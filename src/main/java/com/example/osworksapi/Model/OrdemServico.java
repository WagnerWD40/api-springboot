package com.example.osworksapi.Model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.osworksapi.exception.NegocioException;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios = new ArrayList<Comentario>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrdemServico getStatus() {
        return this.status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public OffsetDateTime getDataAbertura() {
        return this.dataAbertura;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrdemServico)) {
            return false;
        }
        OrdemServico ordemServico = (OrdemServico) o;
        return Objects.equals(id, ordemServico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalização() {
        return this.dataFinalizacao;
    }

    public void setDataFinalização(OffsetDateTime dataFinalização) {
        this.dataFinalizacao = dataFinalização;
    }

	public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Ordem de serviço não pode ser finalizada");
        }
        
        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalização(OffsetDateTime.now());
    }
    
    private boolean naoPodeSerFinalizada() {
        return !StatusOrdemServico.ABERTA.equals(getStatus());
    }
}