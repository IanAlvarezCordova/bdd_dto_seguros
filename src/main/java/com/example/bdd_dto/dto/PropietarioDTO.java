//File: /src/main/java/com/example/bdd_dto/dto/PropietarioDTO.java
package com.example.bdd_dto.dto;

import java.util.List;

public class PropietarioDTO {
    private Long id;
    private String nombreCompleto;
    private int edad;
    //Lista de identificadores de autos
    private List<Long> automovilIds;

    //Constructor vacio
    public PropietarioDTO() {
    }

    //Constructor personalizado
    public PropietarioDTO(Long id, String nombre, String apellido, int edad, List<Long> automovilIds) {
        this.id = id;
        this.nombreCompleto = nombre + " " + apellido;
        this.edad = edad;
        this.automovilIds = automovilIds;
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Long> getAutomovilIds() {
        return automovilIds;
    }

    public void setAutomovilIds(List<Long> automovilIds) {
        this.automovilIds = automovilIds;
    }
}
