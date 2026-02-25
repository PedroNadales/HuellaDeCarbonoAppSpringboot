package org.pedro.huelladecarbonospringboot.controller;

import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.CategoriaRequest;
import org.pedro.huelladecarbonospringboot.model.Categoria;
import org.pedro.huelladecarbonospringboot.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> getAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@Valid @RequestBody CategoriaRequest request) {
        return categoriaService.create(request);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        return categoriaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoriaService.delete(id);
    }
}
