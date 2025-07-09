package com.example.demo.dto;


import com.example.demo.model.EstadoTurno;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TurnoDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El servicio es obligatorio")
    private String servicio;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser anterior a hoy")
    private LocalDate fecha;

    private EstadoTurno estado = EstadoTurno.PENDIENTE;

    // Getters y setters

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
