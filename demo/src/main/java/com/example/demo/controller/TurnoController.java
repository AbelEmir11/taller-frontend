
package com.example.demo.controller;

import com.example.demo.dto.TurnoDTO;
import com.example.demo.model.EstadoTurno;
import com.example.demo.model.Turno;
import com.example.demo.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Controller
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping("/turno")
    public String formulario(Model model, @RequestParam(value = "exito", required = false) String exito) {
        model.addAttribute("turno", new TurnoDTO());
        model.addAttribute("turnos", turnoRepository.findAll());

        if (exito != null) {
            model.addAttribute("mensaje", "¡Turno cargado correctamente!");
        }

        return "turnos"; // tu archivo HTML
    }

    @PostMapping("/turnos")
    public String procesarFormulario(@Valid @ModelAttribute("turno") TurnoDTO turnoDTO,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("turnos", turnoRepository.findAll());
            return "turnos";
        }
        // Convertimos el DTO a entidad
        Turno turno = new Turno();
        turno.setNombre(turnoDTO.getNombre());
        turno.setServicio(turnoDTO.getServicio());
        turno.setFecha(turnoDTO.getFecha());

        turnoRepository.save(turno);
        model.addAttribute("mensaje", "Turno cargado correctamente");
        model.addAttribute("turno", new Turno());
        model.addAttribute("turnos", turnoRepository.findAll());
        return "turnos";
    }

    @PostMapping("/turnos/{id}/estado")
    public String actualizarEstado(@PathVariable Long id, @RequestParam EstadoTurno estado) {
        Optional<Turno> turnoOpt = turnoRepository.findById(id);
        if (turnoOpt.isPresent()) {
            Turno turno = turnoOpt.get();
            turno.setEstado(estado);
            turnoRepository.save(turno);
        }
        return "redirect:/turno"; // redirige a la página de turnos
    }

}
