package com.example.osworksapi.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problema {
    
    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    public static class Campo {

        private String nome;
        private String mensagem;

        Campo(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return this.nome;
        }

        public void setMensagem(String mensage) {
            this.mensagem = mensage;
        }

        public String getMensagem() {
            return this.mensagem;
        }

    }

    public Problema() {
    }

    public Problema(Integer status, OffsetDateTime dataHora, String titulo, ArrayList<Campo> campos) {
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
        this.campos = campos;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Problema status(Integer status) {
        this.status = status;
        return this;
    }

    public Problema dataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public Problema titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public List<Campo> getCampos() {
        return this.campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Problema)) {
            return false;
        }
        Problema problema = (Problema) o;
        return Objects.equals(status, problema.status) && Objects.equals(dataHora, problema.dataHora) && Objects.equals(titulo, problema.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dataHora, titulo);
    }

    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", dataHora='" + getDataHora() + "'" +
            ", titulo='" + getTitulo() + "'" +
            "}";
    }

}