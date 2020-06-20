package com.example.osworksapi.apimodel;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {
    
    @NotNull
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}