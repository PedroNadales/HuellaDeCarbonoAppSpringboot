package org.pedro.huelladecarbonospringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.HuellaRequest;
import org.pedro.huelladecarbonospringboot.model.Huella;
import org.pedro.huelladecarbonospringboot.model.TipoHabito;
import org.pedro.huelladecarbonospringboot.service.HuellaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para el registro de huellas de carbono.
 */
@RestController
@RequestMapping("/api/huellas")
@Tag(name = "Huellas", description = "Registro de huellas de carbono y operaciones avanzadas")
public class HuellaController {

    private final HuellaService huellaService;

    public HuellaController(HuellaService huellaService) {
        this.huellaService = huellaService;
    }

    /**
     * Obtiene huellas con filtro opcional por usuario.
     *
     * @param usuarioId identificador opcional del usuario
     * @return lista de huellas
     */
    @GetMapping
    @Operation(summary = "Listar huellas", description = "Devuelve todas las huellas o filtra por usuario")
    public List<Huella> getAll(@Parameter(description = "Filtro opcional por ID de usuario") @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return huellaService.findByUsuario(usuarioId);
        }
        return huellaService.findAll();
    }

    /**
     * Obtiene una huella por su identificador.
     *
     * @param id identificador de huella
     * @return huella encontrada
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener huella por ID", description = "Devuelve un registro de huella concreto")
    public Huella getById(@Parameter(description = "ID de la huella") @PathVariable Long id) {
        return huellaService.findById(id);
    }

    /**
     * Crea una nueva huella.
     *
     * @param request datos de la huella
     * @return huella creada
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear huella", description = "Registra una nueva huella asociada a usuario y actividad")
    public Huella create(@Valid @RequestBody HuellaRequest request) {
        return huellaService.create(request);
    }

    /**
     * Actualiza una huella existente.
     *
     * @param id identificador de huella
     * @param request datos actualizados
     * @return huella actualizada
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar huella", description = "Actualiza un registro de huella existente")
    public Huella update(@Parameter(description = "ID de la huella") @PathVariable Long id, @Valid @RequestBody HuellaRequest request) {
        return huellaService.update(id, request);
    }

    /**
     * Elimina una huella por su identificador.
     *
     * @param id identificador de huella
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar huella", description = "Elimina un registro de huella por ID")
    public void delete(@Parameter(description = "ID de la huella") @PathVariable Long id) {
        huellaService.delete(id);
    }

    /**
     * Registra una huella y actualiza o crea el habito relacionado.
     *
     * @param request datos de la huella
     * @param tipoHabito periodicidad del habito a registrar
     * @return huella creada
     */
    @PostMapping("/registrar-con-habito")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar huella y actualizar habito", description = "Funcionalidad avanzada: crea la huella y actualiza/crea el habito del usuario para esa actividad")
    public Huella registrarConHabito(
            @Valid @RequestBody HuellaRequest request,
            @Parameter(description = "Tipo de habito a registrar") @RequestParam(defaultValue = "DIARIA") TipoHabito tipoHabito
    ) {
        return huellaService.registrarHuellaYActualizarHabito(request, tipoHabito);
    }
}
