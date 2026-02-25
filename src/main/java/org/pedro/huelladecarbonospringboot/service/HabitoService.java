package org.pedro.huelladecarbonospringboot.service;

import org.pedro.huelladecarbonospringboot.dto.HabitoRequest;
import org.pedro.huelladecarbonospringboot.model.*;
import org.pedro.huelladecarbonospringboot.repository.HabitoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;
    private final UsuarioService usuarioService;
    private final ActividadService actividadService;

    public HabitoService(HabitoRepository habitoRepository, UsuarioService usuarioService, ActividadService actividadService) {
        this.habitoRepository = habitoRepository;
        this.usuarioService = usuarioService;
        this.actividadService = actividadService;
    }

    public List<Habito> findAll() {
        return habitoRepository.findAll();
    }

    public List<Habito> findByUsuario(Long usuarioId) {
        return habitoRepository.findByUsuarioId(usuarioId);
    }

    public Habito findById(HabitoId id) {
        return habitoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habito no encontrado"));
    }

    public Habito upsert(HabitoRequest request) {
        Usuario usuario = usuarioService.findById(request.getUsuarioId());
        Actividad actividad = actividadService.findById(request.getActividadId());

        HabitoId id = new HabitoId(request.getUsuarioId(), request.getActividadId());
        Habito habito = habitoRepository.findById(id).orElseGet(Habito::new);

        habito.setId(id);
        habito.setUsuario(usuario);
        habito.setActividad(actividad);
        habito.setFrecuencia(request.getFrecuencia());
        habito.setTipo(request.getTipo());
        habito.setUltimaFecha(request.getUltimaFecha());
        if (habito.getFechaRegistro() == null) {
            habito.setFechaRegistro(LocalDate.now());
        }

        return habitoRepository.save(habito);
    }

    public void delete(Long usuarioId, Long actividadId) {
        HabitoId id = new HabitoId(usuarioId, actividadId);
        Habito existing = findById(id);
        habitoRepository.delete(existing);
    }
}
