package org.pedro.huelladecarbonospringboot.service;

import org.pedro.huelladecarbonospringboot.dto.ActividadRequest;
import org.pedro.huelladecarbonospringboot.model.Actividad;
import org.pedro.huelladecarbonospringboot.model.Categoria;
import org.pedro.huelladecarbonospringboot.repository.ActividadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final CategoriaService categoriaService;

    public ActividadService(ActividadRepository actividadRepository, CategoriaService categoriaService) {
        this.actividadRepository = actividadRepository;
        this.categoriaService = categoriaService;
    }

    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

    public Actividad findById(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actividad no encontrada"));
    }

    public List<Actividad> findByCategoria(Long categoriaId) {
        return actividadRepository.findByCategoriaId(categoriaId);
    }

    public Actividad create(ActividadRequest request) {
        Categoria categoria = categoriaService.findById(request.getCategoriaId());
        Actividad actividad = new Actividad();
        actividad.setNombre(request.getNombre());
        actividad.setCategoria(categoria);
        return actividadRepository.save(actividad);
    }

    public Actividad update(Long id, ActividadRequest request) {
        Actividad existing = findById(id);
        Categoria categoria = categoriaService.findById(request.getCategoriaId());
        existing.setNombre(request.getNombre());
        existing.setCategoria(categoria);
        return actividadRepository.save(existing);
    }

    public void delete(Long id) {
        Actividad existing = findById(id);
        actividadRepository.delete(existing);
    }
}
