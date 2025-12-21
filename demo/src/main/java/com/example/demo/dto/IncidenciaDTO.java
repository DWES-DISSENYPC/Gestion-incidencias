package com.example.demo.dto;

import java.time.LocalDateTime;
import com.example.demo.model.Estado;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class IncidenciaDTO {

    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private Estado estado; 
    private TecnicoDTO tecnico;

    // los datos de la factura en lugar del objeto
    private LocalDateTime fechaFactura;
    private Double importe;


    //Los datos del tecnico.
    private Long tecnico_id;
    private String nombre_t;


}
