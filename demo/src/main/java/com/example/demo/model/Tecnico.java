package com.example.demo.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // INDICAR QUE SOLO HAY UNA TABLA QUE TENDRA LAS ENTIDADES HEREDADAS
@DiscriminatorColumn(name = "tipo_tecnico") // NOMBRE DE LA COLUMNA QUE DISTINGUIRA EL SUBTIPO
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para decirle a spring que esta el la clave y que es autogenerativa
    private Long id;

    @NotBlank
    private String nombre;

    @Embedded // Se introducen todos los argumentos de esa clase en esta clase.
    private Contacto contacto;

    private Integer experiencia;

    // La relaciones son unidireccionales por eso aqui no se ponen, al ser oneToMay si se pusiera 
    //En tecnico habria una lista

    // metodos que no se rellenan aqui por que se sobreecriben en los hijos
    public abstract String getEntorno();
    public abstract void setEntorno(String entorno);
    public abstract String getTipo();
    


}
