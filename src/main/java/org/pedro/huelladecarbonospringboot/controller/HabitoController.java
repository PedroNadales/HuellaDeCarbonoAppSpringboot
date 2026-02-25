package org.pedro.huelladecarbonospringboot.controller;

import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.HabitoRequest;
import org.pedro.huelladecarbonospringboot.model.Habito;
import org.pedro.huelladecarbonospringboot.service.HabitoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    @GetMapping
    public List<Habito> getAll(@RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return habitoService.findByUsuario(usuarioId);
        }
        return habitoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habito upsert(@Valid @RequestBody HabitoRequest request) {
        return habitoService.upsert(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long usuarioId, @RequestParam Long actividadId) {
        habitoService.delete(usuarioId, actividadId);
    }
}
