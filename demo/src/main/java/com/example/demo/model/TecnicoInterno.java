package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("INTERNO")
public class TecnicoInterno extends Tecnico {

    private String departamento;

    public TecnicoInterno(Long id, String nombre, Contacto contacto, Integer experiencia, String departamento){
        super(id, nombre, contacto, experiencia);
        this.departamento = departamento;
    }

    @Override
    public String getEntorno() {
        return departamento;
    }

    @Override
    public void setEntorno(String entorno) {
        this.departamento = entorno;
    }

    @Override
    public String getTipo() {
       return "INTERNO";
    }

}
