package org.pedro.huelladecarbonospringboot.repository;

import org.pedro.huelladecarbonospringboot.model.Habito;
import org.pedro.huelladecarbonospringboot.model.HabitoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitoRepository extends JpaRepository<Habito, HabitoId> {
    List<Habito> findByUsuarioId(Long usuarioId);
}
