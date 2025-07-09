package com.example.demo.controller;

import com.example.demo.model.Egreso;
import com.example.demo.repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/egresos")
public class EgresoController {

    @Autowired
    private EgresoRepository egresoRepository;

    @GetMapping
    public String listarEgresos(Model model) {
        model.addAttribute("egresos", egresoRepository.findAll());
        model.addAttribute("egreso", new Egreso()); // para el formulario
        model.addAttribute("totalEgresos", egresoRepository.findAll()
                .stream()
                .mapToDouble(ing -> ing.getMonto()).sum()); // Total

        return "egresos"; // vista egresos.html
    }

    @PostMapping
    public String guardarEgreso(@ModelAttribute Egreso egreso) {
        egresoRepository.save(egreso);
        return "redirect:/egresos";
    }
}
