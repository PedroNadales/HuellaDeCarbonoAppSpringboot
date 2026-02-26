package org.pedro.huelladecarbonospringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Datos de entrada para crear o actualizar una categoria")
public class CategoriaRequest {
    @Schema(description = "Nombre de la categoria", example = "Transporte")
    @NotBlank
    private String nombre;
    @Schema(description = "Factor de emision de la categoria", example = "0.21")
    @PositiveOrZero
    private double factorEmision;
    @Schema(description = "Unidad de medida para la actividad de la categoria", example = "km")
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
