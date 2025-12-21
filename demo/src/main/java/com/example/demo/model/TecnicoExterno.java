package com.example.demo.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EXTERNO")
public class TecnicoExterno extends Tecnico{

    private String empresa;

    public TecnicoExterno(Long id, String nombre, Contacto contacto, Integer experiencia, String empresa) {

        super(id, nombre, contacto, experiencia);
        this.empresa = empresa;
    }

    @Override
    public String getEntorno() {

        return this.empresa;
        
    }

    @Override
    public void setEntorno(String entorno) {
        this.empresa = entorno;
    }

    @Override
    public String getTipo() {
        
        return "EXTERNO";
    }

}
