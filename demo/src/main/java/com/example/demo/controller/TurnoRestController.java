package com.example.demo.controller;

import com.example.demo.model.Turno;
import com.example.demo.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;


import java.util.List;

@RestController
@RequestMapping("/api/turnos") // prefijo REST
@CrossOrigin(origins = "*") // permitir llamadas desde el frontend

public class TurnoRestController {
    @Autowired
    private TurnoRepository turnoRepository;

    // GET /api/turnos -> obtener todos los turnos
    @GetMapping
    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    // POST /api/turnos -> crear un nuevo turno
    @PostMapping
    public Turno crearTurno(@RequestBody Turno turno) {
        return turnoRepository.save(turno);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTurnoPorId(@PathVariable Long id) {
        Optional<Turno> turnoOpt = turnoRepository.findById(id);

        if (turnoOpt.isPresent()) {
            return ResponseEntity.ok(turnoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Turno con ID " + id + " no encontrado.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable Long id, @RequestBody Turno turnoActualizado) {
        Optional<Turno> turnoExistente = turnoRepository.findById(id);
        if (turnoExistente.isPresent()) {
            Turno turno = turnoExistente.get();
            turno.setNombre(turnoActualizado.getNombre());
            turno.setFecha(turnoActualizado.getFecha());

            // y cualquier otro campo que tengas
            turnoRepository.save(turno);
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
