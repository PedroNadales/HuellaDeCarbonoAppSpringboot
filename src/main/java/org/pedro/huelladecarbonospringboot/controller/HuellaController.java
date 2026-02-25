package org.pedro.huelladecarbonospringboot.controller;

import jakarta.validation.Valid;
import org.pedro.huelladecarbonospringboot.dto.HuellaRequest;
import org.pedro.huelladecarbonospringboot.model.Huella;
import org.pedro.huelladecarbonospringboot.model.TipoHabito;
import org.pedro.huelladecarbonospringboot.service.HuellaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/huellas")
public class HuellaController {

    private final HuellaService huellaService;

    public HuellaController(HuellaService huellaService) {
        this.huellaService = huellaService;
    }

    @GetMapping
    public List<Huella> getAll(@RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return huellaService.findByUsuario(usuarioId);
        }
        return huellaService.findAll();
    }

    @GetMapping("/{id}")
    public Huella getById(@PathVariable Long id) {
        return huellaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Huella create(@Valid @RequestBody HuellaRequest request) {
        return huellaService.create(request);
    }

    @PutMapping("/{id}")
    public Huella update(@PathVariable Long id, @Valid @RequestBody HuellaRequest request) {
        return huellaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        huellaService.delete(id);
    }

    @PostMapping("/registrar-con-habito")
    @ResponseStatus(HttpStatus.CREATED)
    public Huella registrarConHabito(
            @Valid @RequestBody HuellaRequest request,
            @RequestParam(defaultValue = "DIARIA") TipoHabito tipoHabito
    ) {
        return huellaService.registrarHuellaYActualizarHabito(request, tipoHabito);
    }
}
