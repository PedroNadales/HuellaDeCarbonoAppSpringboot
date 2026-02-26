package org.pedro.huelladecarbonospringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos de entrada para crear o actualizar una actividad")
public class ActividadRequest {
    @Schema(description = "Nombre de la actividad", example = "Coche gasolina")
    @NotBlank
    private String nombre;
    @Schema(description = "ID de la categoria asociada", example = "1")
    @NotNull
    private Long categoriaId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
