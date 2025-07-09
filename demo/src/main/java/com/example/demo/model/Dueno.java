package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Dueno extends Usuario {

    private String dni;

    public Dueno() {}

    public Dueno(String nombre, String correo, String dni) {
        super(nombre, correo);
        this.dni = dni;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}

