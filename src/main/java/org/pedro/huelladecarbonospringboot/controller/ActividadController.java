package org.pedro.huelladecarbonospringboot.controller;

import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.ActividadRequest;
import org.pedro.huelladecarbonospringboot.model.Actividad;
import org.pedro.huelladecarbonospringboot.service.ActividadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping
    public List<Actividad> getAll(@RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return actividadService.findByCategoria(categoriaId);
        }
        return actividadService.findAll();
    }

    @GetMapping("/{id}")
    public Actividad getById(@PathVariable Long id) {
        return actividadService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actividad create(@Valid @RequestBody ActividadRequest request) {
        return actividadService.create(request);
    }

    @PutMapping("/{id}")
    public Actividad update(@PathVariable Long id, @Valid @RequestBody ActividadRequest request) {
        return actividadService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        actividadService.delete(id);
    }
}
