package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Tecnico extends Usuario {

    private String especialidad;

    public Tecnico() {}

    public Tecnico(String nombre, String correo, String especialidad) {
        super(nombre, correo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
