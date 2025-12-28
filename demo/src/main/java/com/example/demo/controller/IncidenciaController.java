package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.service.FacturaIncidenciaService;
import com.example.demo.service.IncidenciaService;
import com.example.demo.service.TecnicoService;
import com.example.demo.dto.FacturaDTO;
import com.example.demo.dto.IncidenciaDTO;
import com.example.demo.dto.IncidenciaEditarDTO;
import com.example.demo.dto.TecnicoDTO;
import com.example.demo.model.Estado;
import com.example.demo.model.Filtro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("incidencias")
public class IncidenciaController {

    private final FacturaIncidenciaService facturaIncidenciaService;

    private IncidenciaService incidenciaService;
    private TecnicoService tecnicoService;

    public IncidenciaController(IncidenciaService incidenciaService, TecnicoService tecnicoService, FacturaIncidenciaService facturaIncidenciaService) {

        this.incidenciaService = incidenciaService;
        this.tecnicoService = tecnicoService;
        this.facturaIncidenciaService = facturaIncidenciaService;

    }

    @GetMapping
    public String listar(Model model,
            @RequestParam(defaultValue = "NONE") String filtro,
            @RequestParam(defaultValue = "") String cadena) {
        model.addAttribute("estados", Estado.values());
        model.addAttribute("filtros", Filtro.values());
        List<IncidenciaDTO> lista = incidenciaService.listarIncidenciasPorFiltro(filtro, cadena);
        model.addAttribute(lista);

        return "incidencias/lista-incidencia";
    }

    @GetMapping("/{id}")
    public String incidenciaPorIdString(@PathVariable Long id, Model model) {

        Optional<IncidenciaDTO> opt = incidenciaService.obtenerIncidenciaPorId(id);
        if (opt.isEmpty())
            return "redirect:/incidencias";
        model.addAttribute("i", opt.get());
        model.addAttribute("ABIERTO", Estado.ABIERTO);
        model.addAttribute("EN_PROCESO", Estado.EN_PROCESO);
        model.addAttribute("RESUELTO", Estado.RESUELTO);
        return new String();
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {

        IncidenciaDTO dto = new IncidenciaDTO();
        model.addAttribute("i", dto);

        return "incidencias/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(@ModelAttribute IncidenciaDTO dto, Model model) {
        incidenciaService.guardarIncidencia(dto);

        return "redirect:/inidecias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        List<TecnicoDTO> tecnicos = tecnicoService.listarTecnicos();
        IncidenciaEditarDTO idto = new IncidenciaEditarDTO(id, null);
        model.addAttribute("i", idto);
        model.addAttribute("tecnicos", tecnicos);
        return "incidencias/procesar-incidencias";
    }

    @PostMapping("/editar/{id}")
    public String editar(@ModelAttribute IncidenciaEditarDTO idto) {
        incidenciaService.actualizarIncidencia(idto);

        return "redirect:/incidencas";
    }

    @GetMapping("/resolver/{id}")
    public String reolver(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("factura", new FacturaDTO());

        return "facturas/factura-incidencia";
    }

    @PostMapping("/resolver/{id}")
    public String resolver(@ModelAttribute FacturaDTO fdto, @PathVariable Long id) {
        facturaIncidenciaService.nuevaFactura(fdto, id);
        return "redirect:/incidencias";
    }
    

}
