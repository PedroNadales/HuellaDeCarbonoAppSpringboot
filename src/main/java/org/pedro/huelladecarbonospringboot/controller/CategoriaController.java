package org.pedro.huelladecarbonospringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.CategoriaRequest;
import org.pedro.huelladecarbonospringboot.model.Categoria;
import org.pedro.huelladecarbonospringboot.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para la gestion de categorias.
 */
@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gestion de categorias de emision")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Obtiene todas las categorias.
     *
     * @return lista de categorias
     */
    @GetMapping
    @Operation(summary = "Listar categorias", description = "Devuelve todas las categorias registradas")
    public List<Categoria> getAll() {
        return categoriaService.findAll();
    }

    /**
     * Obtiene una categoria por ID.
     *
     * @param id identificador de categoria
     * @return categoria encontrada
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria por ID", description = "Devuelve una categoria concreta por identificador")
    public Categoria getById(@Parameter(description = "ID de la categoria") @PathVariable Long id) {
        return categoriaService.findById(id);
    }

    /**
     * Crea una categoria.
     *
     * @param request datos de la categoria
     * @return categoria creada
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear categoria", description = "Crea una nueva categoria de emision")
    public Categoria create(@Valid @RequestBody CategoriaRequest request) {
        return categoriaService.create(request);
    }

    /**
     * Actualiza una categoria existente.
     *
     * @param id identificador de categoria
     * @param request datos actualizados
     * @return categoria actualizada
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoria", description = "Actualiza los datos de una categoria existente")
    public Categoria update(@Parameter(description = "ID de la categoria") @PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        return categoriaService.update(id, request);
    }

    /**
     * Elimina una categoria por ID.
     *
     * @param id identificador de categoria
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar categoria", description = "Elimina una categoria por ID")
    public void delete(@Parameter(description = "ID de la categoria") @PathVariable Long id) {
        categoriaService.delete(id);
    }
}
