package com.example.demo.dto;

import java.time.LocalDateTime;
import com.example.demo.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaIncidencia;
    private LocalDateTime fechaFactura;
    private Estado estado;
    private Double importe;


}
