package org.pedro.huelladecarbonospringboot.repository;

import org.pedro.huelladecarbonospringboot.model.Huella;
import org.pedro.huelladecarbonospringboot.repository.projection.ImpactoCategoriaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HuellaRepository extends JpaRepository<Huella, Long> {

    List<Huella> findByUsuarioId(Long usuarioId);

    @Query("""
            SELECT c.nombre AS categoria, SUM(h.valor * c.factorEmision) AS impacto
            FROM Huella h
            JOIN h.actividad a
            JOIN a.categoria c
            WHERE h.usuario.id = :usuarioId
            GROUP BY c.nombre
            ORDER BY c.nombre
            """)
    List<ImpactoCategoriaView> calcularImpactoPorCategoria(@Param("usuarioId") Long usuarioId);
}
