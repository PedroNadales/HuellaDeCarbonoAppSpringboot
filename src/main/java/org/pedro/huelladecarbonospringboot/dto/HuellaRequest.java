package org.pedro.huelladecarbonospringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Schema(description = "Datos de entrada para crear o actualizar una huella de carbono")
public class HuellaRequest {
    @Schema(description = "Valor consumido de la actividad", example = "35.0")
    @PositiveOrZero
    private double valor;
    @Schema(description = "Fecha del registro", example = "2026-02-24")
    @NotNull
    private LocalDate fecha;
    @Schema(description = "ID de la actividad asociada", example = "1")
    @NotNull
    private Long actividadId;
    @Schema(description = "ID del usuario propietario del registro", example = "1")
    @NotNull
    private Long usuarioId;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
