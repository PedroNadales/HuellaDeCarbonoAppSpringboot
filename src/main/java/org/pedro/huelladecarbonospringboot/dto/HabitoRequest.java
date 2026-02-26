package org.pedro.huelladecarbonospringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.pedro.huelladecarbonospringboot.model.TipoHabito;

import java.time.LocalDate;

@Schema(description = "Datos de entrada para crear o actualizar un habito")
public class HabitoRequest {
    @Schema(description = "ID del usuario", example = "1")
    @NotNull
    private Long usuarioId;
    @Schema(description = "ID de la actividad", example = "1")
    @NotNull
    private Long actividadId;
    @Schema(description = "Frecuencia de realizacion del habito", example = "5")
    @PositiveOrZero
    private double frecuencia;
    @Schema(description = "Periodicidad del habito", example = "SEMANAL")
    @NotNull
    private TipoHabito tipo;
    @Schema(description = "Ultima fecha en que se realizo el habito", example = "2026-02-24")
    @NotNull
    private LocalDate ultimaFecha;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public TipoHabito getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabito tipo) {
        this.tipo = tipo;
    }

    public LocalDate getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(LocalDate ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }
}
