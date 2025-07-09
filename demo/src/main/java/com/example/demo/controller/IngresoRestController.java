package com.example.demo.controller;

import com.example.demo.model.Ingreso;
import com.example.demo.repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
@CrossOrigin(origins = "*") // Permite que el frontend acceda desde otro origen
public class IngresoRestController {

    @Autowired
    private IngresoRepository ingresoRepository;

    @GetMapping
    public List<Ingreso> obtenerIngresos() {
        return ingresoRepository.findAll(); // Devuelve JSON automáticamente
    }

    @PostMapping
    public Ingreso guardarIngreso(@RequestBody Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }
}
