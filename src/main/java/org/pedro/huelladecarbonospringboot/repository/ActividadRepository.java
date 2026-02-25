package org.pedro.huelladecarbonospringboot.repository;

import org.pedro.huelladecarbonospringboot.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    List<Actividad> findByCategoriaId(Long categoriaId);
}
