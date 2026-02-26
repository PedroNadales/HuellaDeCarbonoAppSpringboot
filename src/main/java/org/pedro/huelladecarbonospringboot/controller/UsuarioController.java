package org.pedro.huelladecarbonospringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.UsuarioRequest;
import org.pedro.huelladecarbonospringboot.model.Usuario;
import org.pedro.huelladecarbonospringboot.service.HuellaService;
import org.pedro.huelladecarbonospringboot.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Endpoints REST para la gestion de usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD y metricas de impacto de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final HuellaService huellaService;

    public UsuarioController(UsuarioService usuarioService, HuellaService huellaService) {
        this.usuarioService = usuarioService;
        this.huellaService = huellaService;
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return lista de usuarios
     */
    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Devuelve todos los usuarios registrados")
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return usuario encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario concreto a partir de su identificador")
    public Usuario getById(@Parameter(description = "ID del usuario") @PathVariable Long id) {
        return usuarioService.findById(id);
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param request datos de entrada del usuario
     * @return usuario creado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario con validacion de email unico")
    public Usuario create(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.create(request);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id identificador del usuario
     * @param request datos de usuario a actualizar
     * @return usuario actualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    public Usuario update(@Parameter(description = "ID del usuario") @PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.update(id, request);
    }

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su identificador")
    public void delete(@Parameter(description = "ID del usuario") @PathVariable Long id) {
        usuarioService.delete(id);
    }

    /**
     * Calcula el impacto total de huella para un usuario.
     *
     * @param id identificador del usuario
     * @return mapa con usuario e impacto total
     */
    @GetMapping("/{id}/impacto-total")
    @Operation(summary = "Impacto total por usuario", description = "Calcula el impacto total acumulado del usuario")
    public Map<String, Object> getImpactoTotal(@Parameter(description = "ID del usuario") @PathVariable Long id) {
        return Map.of(
                "usuarioId", id,
                "impactoTotal", huellaService.calcularImpactoTotal(id)
        );
    }

    /**
     * Obtiene el impacto agrupado por categoria para un usuario.
     *
     * @param id identificador del usuario
     * @return lista de impactos por categoria
     */
    @GetMapping("/{id}/impacto-por-categoria")
    @Operation(summary = "Impacto por categoria", description = "Devuelve el impacto agrupado por categoria para un usuario")
    public Object getImpactoPorCategoria(@Parameter(description = "ID del usuario") @PathVariable Long id) {
        return huellaService.calcularImpactoPorCategoria(id);
    }
}
