package org.pedro.huelladecarbonospringboot.controller;

import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.UsuarioRequest;
import org.pedro.huelladecarbonospringboot.model.Usuario;
import org.pedro.huelladecarbonospringboot.service.HuellaService;
import org.pedro.huelladecarbonospringboot.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final HuellaService huellaService;

    public UsuarioController(UsuarioService usuarioService, HuellaService huellaService) {
        this.usuarioService = usuarioService;
        this.huellaService = huellaService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.create(request);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/{id}/impacto-total")
    public Map<String, Object> getImpactoTotal(@PathVariable Long id) {
        return Map.of(
                "usuarioId", id,
                "impactoTotal", huellaService.calcularImpactoTotal(id)
        );
    }

    @GetMapping("/{id}/impacto-por-categoria")
    public Object getImpactoPorCategoria(@PathVariable Long id) {
        return huellaService.calcularImpactoPorCategoria(id);
    }
}
