package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FacturaDTO;
import com.example.demo.model.Estado;
import com.example.demo.model.FacturaIncidencia;
import com.example.demo.model.Incidencia;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.IncidenciaRepository;

@Service
public class FacturaIncidenciaService {

    FacturaRepository facturaRepository;
    IncidenciaRepository incidenciaRepository;

    public FacturaIncidenciaService (FacturaRepository facturaRepository, IncidenciaRepository incidenciaRepository) {

        this.facturaRepository = facturaRepository;
        this.incidenciaRepository = incidenciaRepository;
    }

    @Transactional
    public void nuevaFactura(FacturaDTO fdto, Long idIncidencia) {

        FacturaIncidencia facturaIncidencia = new FacturaIncidencia(

            null,// este valor lo asigan la propia base de datos
            fdto.getImporte(),
            LocalDateTime.now()

        );

        facturaRepository.save(facturaIncidencia);

        // Al coger de la base de datos la incidencia y despues hacerle cambios a la mismoa en memoria
        // Hivernacle sabe que teine que hacer el Udate de la incidencia (Si han habido cambios en sus valores)
        Incidencia i = incidenciaRepository.findById(idIncidencia).get();
        i.setFacturaIncidencia(facturaIncidencia);
        i.setEstado(Estado.RESUELTO);
    }

    

}
