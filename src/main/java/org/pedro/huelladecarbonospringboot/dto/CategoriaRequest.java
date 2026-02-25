package org.pedro.huelladecarbonospringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class CategoriaRequest {
    @NotBlank
    private String nombre;
    @PositiveOrZero
    private double factorEmision;
    @NotBlank
    private String unidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFactorEmision() {
        return factorEmision;
    }

    public void setFactorEmision(double factorEmision) {
        this.factorEmision = factorEmision;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
