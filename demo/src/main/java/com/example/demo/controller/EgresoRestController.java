package com.example.demo.controller;

import com.example.demo.model.Egreso;
import com.example.demo.repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/egresos")
@CrossOrigin(origins = "*") // Permite llamadas desde tu frontend
public class EgresoRestController {

    @Autowired
    private EgresoRepository egresoRepository;

    @GetMapping
    public List<Egreso> listarEgresos() {
        return egresoRepository.findAll();
    }

    @PostMapping
    public Egreso guardarEgreso(@RequestBody Egreso egreso) {
        return egresoRepository.save(egreso);
    }
}
