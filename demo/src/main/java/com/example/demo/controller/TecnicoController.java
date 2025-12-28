package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.TecnicoDTO;
import com.example.demo.service.TecnicoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoController {

    private TecnicoService tecnicoService;

    public TecnicoController (TecnicoService tecnicoService) {

        this.tecnicoService = tecnicoService;

    }

    @GetMapping
    public String listar(Model model) {

        List<TecnicoDTO> lista = tecnicoService.listarTecnicos();
        model.addAttribute("lista", lista);
        return "tecnicos/lista-tecnicos";

    }

    @GetMapping("/{id}")
    public String mostrarPorId(@PathVariable Long id, Model model) {

        Optional<TecnicoDTO> opt = tecnicoService.mostrarTecnicoPorId(id);
        if(opt.isEmpty()) return "redirect:/";
        model.addAttribute("t", opt.get());
        return new String();
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("t", new TecnicoDTO());
        return "tecnicos/nuevo-tecnico";
    }

    @PostMapping("/nuevo")
    public String nuevo(Model model, @ModelAttribute TecnicoDTO dto) {

        tecnicoService.guardar(dto);
        
        return "redirect:/tecnicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Optional<TecnicoDTO> opt = tecnicoService.mostrarTecnicoPorId(id);
        if(opt.isEmpty()) return "redirect:/";
        model.addAttribute("t", opt.get());

        return "/tecnicos/editar-tecnico";
    }

    @PostMapping("/editar/{id}")
    public String editar(Model model, @ModelAttribute TecnicoDTO dto) {
        
        tecnicoService.guardar(dto);
        
        return "redirect:/tecnico";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        tecnicoService.eliminar(id);

        return "redirect:/tecnicos";
    }
    
}
