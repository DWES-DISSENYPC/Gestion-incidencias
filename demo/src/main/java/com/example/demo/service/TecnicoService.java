package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TecnicoDTO;
import com.example.demo.model.Tecnico;
import com.example.demo.model.TecnicoExterno;
import com.example.demo.model.TecnicoInterno;
import com.example.demo.repository.TecnicoRepository;

import jakarta.transaction.Transactional;

@Service
public class TecnicoService {

    TecnicoRepository tecnicoRepository;
    
    public TecnicoService (TecnicoRepository tecnicoRepository){

        this.tecnicoRepository = tecnicoRepository;

    }

    @Transactional
    public List<TecnicoDTO> listarTecnicos() {

        List<Tecnico> lista = tecnicoRepository.findAll();
        List<TecnicoDTO> listaDto = new ArrayList<>();

        for (Tecnico t : lista) {

            listaDto.add(entityToDto(t));

        }

        return listaDto;
    }

    @Transactional
    public Optional<TecnicoDTO>  mostrarTecnicoPorId(Long id) {

        Optional<Tecnico> tecnicoOpt = tecnicoRepository.findById(id);

        if (tecnicoOpt.isEmpty()) return Optional.empty();
        return Optional.of(entityToDto(tecnicoOpt.get()));

    }

    @Transactional
    public void guardar(TecnicoDTO dto){

        tecnicoRepository.save(dtoToEntity(dto));

    }

    @Transactional
    public void eliminar(Long id) {

        tecnicoRepository.deleteById(id);

    }
    
    private Tecnico dtoToEntity(TecnicoDTO dto) {
        
        Tecnico tecnico;
        if(dto.getTipo().equals("INTERNO")) tecnico = new TecnicoInterno();
        else tecnico = new TecnicoExterno();
        tecnico.setContacto(dto.getContacto());
        tecnico.setEntorno(dto.getEntorno());
        tecnico.setExperiencia(dto.getExperiencia());
        tecnico.setNombre(dto.getNombre());
        return tecnico;

    }

    public TecnicoDTO entityToDto(Tecnico tecnico){
        return new TecnicoDTO
        (
            tecnico.getId(),
            tecnico.getNombre(),
            tecnico.getContacto(),
            tecnico.getExperiencia(),
            tecnico.getEntorno(),
            tecnico.getTipo()
        );

    }

   


}