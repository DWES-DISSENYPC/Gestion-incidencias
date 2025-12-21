package com.example.demo.dto;

import com.example.demo.model.Contacto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TecnicoDTO {

    // como es un dto no es entidad, ni usa anotaciones de base de datos
    // Tampo es abstract ni hereda ni....
    // Por eso aqui, en lugar de tener los metodos sobreescritos entan directamente los argumentos.
    
    private Long id;
    private String nombre;
    private Contacto contacto;
    private Integer experiencia;
    private String entorno;
    private String tipo;

}
