package org.pedro.huelladecarbonospringboot.service;

import org.pedro.huelladecarbonospringboot.dto.CategoriaRequest;
import org.pedro.huelladecarbonospringboot.model.Categoria;
import org.pedro.huelladecarbonospringboot.repository.CategoriaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria no encontrada"));
    }

    public Categoria create(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setFactorEmision(request.getFactorEmision());
        categoria.setUnidad(request.getUnidad());
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, CategoriaRequest request) {
        Categoria existing = findById(id);
        existing.setNombre(request.getNombre());
        existing.setFactorEmision(request.getFactorEmision());
        existing.setUnidad(request.getUnidad());
        return categoriaRepository.save(existing);
    }

    public void delete(Long id) {
        Categoria existing = findById(id);
        try {
            categoriaRepository.delete(existing);
            categoriaRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se puede eliminar la categoria porque tiene actividades o recomendaciones asociadas"
            );
        }
    }
}
