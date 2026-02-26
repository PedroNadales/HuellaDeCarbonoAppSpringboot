package org.pedro.huelladecarbonospringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.ActividadRequest;
import org.pedro.huelladecarbonospringboot.model.Actividad;
import org.pedro.huelladecarbonospringboot.service.ActividadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para la gestion de actividades.
 */
@RestController
@RequestMapping("/api/actividades")
@Tag(name = "Actividades", description = "Gestion de actividades asociadas a categorias")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * Obtiene actividades, con filtro opcional por categoria.
     *
     * @param categoriaId identificador opcional de categoria
     * @return lista de actividades
     */
    @GetMapping
    @Operation(summary = "Listar actividades", description = "Devuelve actividades. Permite filtrar por categoriaId")
    public List<Actividad> getAll(@Parameter(description = "Filtro opcional por ID de categoria") @RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return actividadService.findByCategoria(categoriaId);
        }
        return actividadService.findAll();
    }

    /**
     * Obtiene una actividad por su ID.
     *
     * @param id identificador de actividad
     * @return actividad encontrada
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener actividad por ID", description = "Devuelve una actividad concreta por identificador")
    public Actividad getById(@Parameter(description = "ID de la actividad") @PathVariable Long id) {
        return actividadService.findById(id);
    }

    /**
     * Crea una actividad.
     *
     * @param request datos de la actividad
     * @return actividad creada
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear actividad", description = "Crea una nueva actividad asociada a una categoria")
    public Actividad create(@Valid @RequestBody ActividadRequest request) {
        return actividadService.create(request);
    }

    /**
     * Actualiza una actividad existente.
     *
     * @param id identificador de actividad
     * @param request datos actualizados
     * @return actividad actualizada
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar actividad", description = "Actualiza una actividad existente")
    public Actividad update(@Parameter(description = "ID de la actividad") @PathVariable Long id, @Valid @RequestBody ActividadRequest request) {
        return actividadService.update(id, request);
    }

    /**
     * Elimina una actividad por ID.
     *
     * @param id identificador de actividad
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar actividad", description = "Elimina una actividad por ID")
    public void delete(@Parameter(description = "ID de la actividad") @PathVariable Long id) {
        actividadService.delete(id);
    }
}
