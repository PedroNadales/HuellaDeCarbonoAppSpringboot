package org.pedro.huelladecarbonospringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HabitoId implements Serializable {

    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column(name = "id_actividad")
    private Long actividadId;

    public HabitoId() {
    }

    public HabitoId(Long usuarioId, Long actividadId) {
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HabitoId habitoId)) {
            return false;
        }
        return Objects.equals(usuarioId, habitoId.usuarioId) && Objects.equals(actividadId, habitoId.actividadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, actividadId);
    }
}
