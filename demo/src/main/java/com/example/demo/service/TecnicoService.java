package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TecnicoDTO;
import com.example.demo.model.Tecnico;
import com.example.demo.repository.TecnicoRepository;

@Service
public class TecnicoService {

    TecnicoRepository tecnicoRepository;
    
    public TecnicoService (TecnicoRepository tecnicoRepository){

        this.tecnicoRepository = tecnicoRepository;

    }

    public List<TecnicoDTO> listarTecnicos() {

        List<Tecnico> lista = tecnicoRepository.findAll();
        List<TecnicoDTO> listaDto = new ArrayList<>();

        for (Tecnico t : lista) {

            listaDto.add(entityToDto(t));

        }

        return listaDto;
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