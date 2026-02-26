package org.pedro.huelladecarbonospringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.HabitoRequest;
import org.pedro.huelladecarbonospringboot.model.Habito;
import org.pedro.huelladecarbonospringboot.service.HabitoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para la gestion de habitos.
 */
@RestController
@RequestMapping("/api/habitos")
@Tag(name = "Habitos", description = "Gestion de habitos por usuario y actividad")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    /**
     * Obtiene habitos con filtro opcional por usuario.
     *
     * @param usuarioId identificador opcional del usuario
     * @return lista de habitos
     */
    @GetMapping
    @Operation(summary = "Listar habitos", description = "Devuelve todos los habitos o filtra por usuario")
    public List<Habito> getAll(@Parameter(description = "Filtro opcional por ID de usuario") @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return habitoService.findByUsuario(usuarioId);
        }
        return habitoService.findAll();
    }

    /**
     * Crea o actualiza un habito.
     *
     * @param request datos de entrada del habito
     * @return habito guardado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear o actualizar habito", description = "Crea un habito nuevo o actualiza uno existente para la clave compuesta usuario-actividad")
    public Habito upsert(@Valid @RequestBody HabitoRequest request) {
        return habitoService.upsert(request);
    }

    /**
     * Elimina un habito por clave compuesta.
     *
     * @param usuarioId identificador del usuario
     * @param actividadId identificador de la actividad
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar habito", description = "Elimina un habito por su clave compuesta usuarioId + actividadId")
    public void delete(
            @Parameter(description = "ID del usuario") @RequestParam Long usuarioId,
            @Parameter(description = "ID de la actividad") @RequestParam Long actividadId
    ) {
        habitoService.delete(usuarioId, actividadId);
    }
}
