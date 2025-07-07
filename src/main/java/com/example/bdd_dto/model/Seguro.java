//File: /src/main/java/com/example/bdd_dto/model/Seguro.java
package com.example.bdd_dto.model;

import jakarta.persistence.*;

@Entity
public class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double costoTotal;

    @OneToOne
    @JoinColumn(name = "automovil_id", nullable = false)
    private Automovil automovil;

    public Seguro() {
    }

    public Seguro(double costoTotal, Automovil automovil) {
        this.costoTotal = costoTotal;
        this.automovil = automovil;
    }

    //Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }
}
