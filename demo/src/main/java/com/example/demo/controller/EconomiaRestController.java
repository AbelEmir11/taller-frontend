package com.example.demo.controller;

import com.example.demo.repository.IngresoRepository;
import com.example.demo.repository.EgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/economia")
@CrossOrigin(origins = "*")
public class EconomiaRestController {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private EgresoRepository egresoRepository;

    private static final double META = 10000000.0; // Podés hacerlo dinámico si querés

    @GetMapping
    public Map<String, Object> obtenerResumen() {
        double totalIngresos = ingresoRepository.findAll()
                .stream()
                .mapToDouble(i -> i.getMonto())
                .sum();

        double totalEgresos = egresoRepository.findAll()
                .stream()
                .mapToDouble(e -> e.getMonto())
                .sum();

        double balance = totalIngresos - totalEgresos;
        double porcentaje = (balance / META) * 100;

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalIngresos", totalIngresos);
        resumen.put("totalEgresos", totalEgresos);
        resumen.put("balance", balance);
        resumen.put("porcentajeAlcanzado", porcentaje);

        return resumen;
    }
}
