package com.example.demo.controller;

import com.example.demo.model.Ingreso;
import com.example.demo.repository.IngresoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingresos")
public class IngresoController {

    @Autowired
    private IngresoRepository ingresoRepository;

    @GetMapping
    public String listarIngresos(Model model) {
        model.addAttribute("ingresos", ingresoRepository.findAll());
        model.addAttribute("ingreso", new Ingreso()); // para el formulario
        model.addAttribute("totalingresos", ingresoRepository.findAll()
                .stream()
                .mapToDouble(ing -> ing.getMonto()).sum()); // Total

        return "ingresos"; // vista
    }

    @PostMapping
    public String guardarIngreso(@ModelAttribute Ingreso ingreso) {
        ingresoRepository.save(ingreso);
        return "redirect:/ingresos";
    }
}
