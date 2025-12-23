package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.IncidenciaDTO;
import com.example.demo.dto.IncidenciaEditarDTO;
import com.example.demo.model.Estado;
import com.example.demo.model.FacturaIncidencia;
import com.example.demo.model.Filtro;
import com.example.demo.model.Incidencia;
import com.example.demo.model.Tecnico;
import com.example.demo.repository.IncidenciaRepository;
import com.example.demo.repository.TecnicoRepository;

import jakarta.transaction.Transactional;

@Service
public class IncidenciaService {

    private IncidenciaRepository incidenciaRepository;
    private TecnicoRepository tecnicoRepository;

    public IncidenciaService (TecnicoRepository tecnicoRepository, IncidenciaRepository incidenciaRepository) {

        this.tecnicoRepository = tecnicoRepository;
        this.incidenciaRepository = incidenciaRepository;

    }

    @Transactional // para marcar que las acciones a la base de datos no se hagan unas si y otras no
    public List<IncidenciaDTO> listarIncidenciasDeTecnico(Long idTecnico){

        List<Incidencia> incidencias = incidenciaRepository.findByTecnicoId(idTecnico);
        List<IncidenciaDTO> listaDTO = new ArrayList<>();
        for (Incidencia i : incidencias) {

            listaDTO.add(entityToDto(i));
        }

        return listaDTO;
    }

    @Transactional
    public List<IncidenciaDTO> listarIncidencias() {
        List<Incidencia> incidencias = incidenciaRepository.findAll();
        List<IncidenciaDTO> listaDTO = new ArrayList<>();
        for (Incidencia i: incidencias) {

            listaDTO.add(entityToDto(i));

        }

        return listaDTO;
    }

    @Transactional
    public List<IncidenciaDTO> listarIncidenciasPorFiltro(String filtro, String cadena){

        List<Incidencia> incidencias;
        List<IncidenciaDTO> listaDTO = new ArrayList<>();

        switch (Filtro.valueOf(filtro)) {
            case Filtro.TITULO:
                incidencias = incidenciaRepository.findAllByTituloContaining(cadena);
                break;
            case Filtro.ESTADO:
                incidencias = incidenciaRepository.findAllByEstado(cadena);
                break;
            case Filtro.T_NOMBRE:
                incidencias= incidenciaRepository.findAllByTituloContaining(cadena);
                break;
            case Filtro.T_EMAIL:
                incidencias = incidenciaRepository.findAllByTecnicoContactoEmail(cadena);
                break;
            default:
                incidencias = incidenciaRepository.findAll();
                break;
        }

        for ( Incidencia i : incidencias) {

            listaDTO.add(entityToDto(i));
        }

        return listaDTO;

    }

    @Transactional
    public Optional<IncidenciaDTO> obtenerIncidenciaPorId(Long id){
        Optional<Incidencia> i = incidenciaRepository.findById(id);
        if(i.isEmpty()) return Optional.empty();
        return Optional.of(entityToDto(i.get()));
    }

    @Transactional
    public void guardarIncidencia(IncidenciaDTO dto){

        Incidencia i = dtoToEntity(dto);
        //Cunado una incidencia se crea nueva no tiene fecha hay que crearla.
        i.setFechaCreacion(LocalDateTime.now());
        // No se le pide al usuario que introduzca el estado por que al ser nueva es ABIERTO
        i.setEstado(Estado.ABIERTO);
        incidenciaRepository.save(i);

    }

    @Transactional
    public void actualizarIncidencia(IncidenciaEditarDTO idto) {

        Optional<Incidencia> incidencia = incidenciaRepository.findById(idto.getIdIncidencia());
        Optional<Tecnico> tecnico = tecnicoRepository.findById(idto.getIdTecnico());
        if (incidencia.isPresent() && tecnico.isPresent()) {
            incidencia.get().setTecnico(tecnico.get());
            incidencia.get().setEstado(Estado.EN_PROCESO);
        }
        // No hay que llamar al save para guardar los datos en la base de datos por que al ser Transaccional
        // Hivernate ya detecta que hay un cambio entre la base de datos y la memoria de la aplicacion
        // y genera el update automaticamente.
        

    }


    //Cuadno se crea una nueva incidencia no tiene ni Tecnico ni Factura asociados
    // Si pidieran la funcion para modificar incidencia, seria necesario que el DTO
    // tuviera ka información del tecnico y de la factura asociados (los id)
    //Seria correcto tantgo modificar el DTO existente como crear otro unicamente para editar
    private Incidencia dtoToEntity(IncidenciaDTO dto) {
        
        return new Incidencia (

            dto.getId(),
            dto.getTitulo(),
            dto.getDescripcion(),
            dto.getFechaFactura(),
            dto.getEstado()

        );

    }

    // No hace falta @Transaccional por que la carga de la factura es EAGER
    private IncidenciaDTO entityToDto(Incidencia i) {
        // La incidencia puede no tener factura. en ese caso los datos de la factura se dejan como null
        FacturaIncidencia f = i.getFacturaIncidencia();

        return new IncidenciaDTO(
            i.getId(),
            i.getTitulo(),
            i.getDescripcion(),
            i.getFechaCreacion(),
            f==null ? null : f.getFechaEmision(),
            i.getEstado(),
            f==null ? null : f.getImporte()

        );


    }

    

}
