package org.pedro.huelladecarbonospringboot.repository;

import org.pedro.huelladecarbonospringboot.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {
    List<Recomendacion> findByCategoriaId(Long categoriaId);
}
