package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    public List<Incidencia> findByTecnicoId(Long id);

    public List<Incidencia> findAllByTituloContaining(String cadena);

    public List<Incidencia> findAllByEstado(String cadena);

    public List<Incidencia> findAllByTecnicoNombreContaining(String cadena);

    public List<Incidencia> findAllByTecnicoContactoEmail(String cadena);
}
