package com.example.osworksapi.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.example.osworksapi.exception.EntidadeNaoEncontradaException;
import com.example.osworksapi.exception.NegocioException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleNegocio(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();

        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }    

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();

        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
            ArrayList<Problema.Campo> campos = new ArrayList<Problema.Campo>();

            for (ObjectError error: ex.getBindingResult().getAllErrors()) {
                String nome = ((FieldError) error).getField();
                String mensagem = error.getDefaultMessage();

                campos.add(new Problema.Campo(nome, mensagem));
            }

            Problema problema = new Problema(
                status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão inválidos."
                + "Faça o preenchimento correto e tente novamente.",
                campos
            );

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}