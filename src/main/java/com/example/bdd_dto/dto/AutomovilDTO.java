//File: /src/main/java/com/example/bdd_dto/dto/AutomovilDTO.java
package com.example.bdd_dto.dto;

public class AutomovilDTO {
    private Long id;
    private String modelo;
    private double valor;
    private int accidentes;
    private Long propietarioId;
    private String propietarioNombre;
    private double costoSeguro;

    public AutomovilDTO() {
    }

    public AutomovilDTO(Long id, String modelo, double valor, int accidentes, Long propietarioId, String propietarioNombre, double costoSeguro) {
        this.id = id;
        this.modelo = modelo;
        this.valor = valor;
        this.accidentes = accidentes;
        this.propietarioId = propietarioId;
        this.propietarioNombre = propietarioNombre;
        this.costoSeguro = costoSeguro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(int accidentes) {
        this.accidentes = accidentes;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Long propietarioId) {
        this.propietarioId = propietarioId;
    }

    public String getPropietarioNombre() {
        return propietarioNombre;
    }

    public void setPropietarioNombre(String propietarioNombre) {
        this.propietarioNombre = propietarioNombre;
    }

    public double getCostoSeguro() {
        return costoSeguro;
    }

    public void setCostoSeguro(double costoSeguro) {
        this.costoSeguro = costoSeguro;
    }
}