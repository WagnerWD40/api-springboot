package com.example.osworksapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
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
                LocalDateTime.now(),
                "Um ou mais campos estão inválidos."
                + "Faça o preenchimento correto e tente novamente.",
                campos
            );

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}