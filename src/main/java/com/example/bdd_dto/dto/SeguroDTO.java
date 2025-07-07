//File: /src/main/java/com/example/bdd_dto/dto/SeguroDTO.java
package com.example.bdd_dto.dto;

public class SeguroDTO {
    private Long id;
    private double costoTotal;
    private Long automovilId;

    public SeguroDTO() {
    }

    public SeguroDTO(Long id, double costoTotal, Long automovilId) {
        this.id = id;
        this.costoTotal = costoTotal;
        this.automovilId = automovilId;
    }

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

    public Long getAutomovilId() {
        return automovilId;
    }

    public void setAutomovilId(Long automovilId) {
        this.automovilId = automovilId;
    }
}
