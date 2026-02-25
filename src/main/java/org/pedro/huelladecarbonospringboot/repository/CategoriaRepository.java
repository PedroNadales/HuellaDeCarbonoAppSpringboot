package org.pedro.huelladecarbonospringboot.repository;

import org.pedro.huelladecarbonospringboot.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
