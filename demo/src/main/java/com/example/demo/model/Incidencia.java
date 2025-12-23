package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING) // Para que se guarde este atributo como texto en la base de datos
    private Estado estado; 

    @ManyToOne(fetch = FetchType.LAZY)
    private Tecnico tecnico;


    //En el caso de oneToOne s la carga "fetch" es EAGER
    @OneToOne 
    private FacturaIncidencia facturaIncidencia;

    //Creamos este constructor sin todos los afgumentos por que cuando se crea una incidencia es posible no saber el 
    // tecnico asignado ni ka factura
    public Incidencia(Long id, String titulo, String descripcion, LocalDateTime fechaCreacion, Estado estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

}
