package com.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // indica que se añade a otras entidades sin crear su prupia tabla
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {

    private int telefono;
    private String email;

}
