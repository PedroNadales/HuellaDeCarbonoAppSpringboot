package org.pedro.huelladecarbonospringboot.service;

import org.pedro.huelladecarbonospringboot.dto.HuellaRequest;
import org.pedro.huelladecarbonospringboot.model.*;
import org.pedro.huelladecarbonospringboot.repository.HabitoRepository;
import org.pedro.huelladecarbonospringboot.repository.HuellaRepository;
import org.pedro.huelladecarbonospringboot.repository.projection.ImpactoCategoriaView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class HuellaService {

    private final HuellaRepository huellaRepository;
    private final HabitoRepository habitoRepository;
    private final UsuarioService usuarioService;
    private final ActividadService actividadService;

    public HuellaService(
            HuellaRepository huellaRepository,
            HabitoRepository habitoRepository,
            UsuarioService usuarioService,
            ActividadService actividadService
    ) {
        this.huellaRepository = huellaRepository;
        this.habitoRepository = habitoRepository;
        this.usuarioService = usuarioService;
        this.actividadService = actividadService;
    }

    public List<Huella> findAll() {
        return huellaRepository.findAll();
    }

    public List<Huella> findByUsuario(Long usuarioId) {
        return huellaRepository.findByUsuarioId(usuarioId);
    }

    public Huella findById(Long id) {
        return huellaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Huella no encontrada"));
    }

    public Huella create(HuellaRequest request) {
        Usuario usuario = usuarioService.findById(request.getUsuarioId());
        Actividad actividad = actividadService.findById(request.getActividadId());

        Huella huella = new Huella();
        huella.setValor(request.getValor());
        huella.setFecha(request.getFecha());
        huella.setUsuario(usuario);
        huella.setActividad(actividad);
        return huellaRepository.save(huella);
    }

    public Huella update(Long id, HuellaRequest request) {
        Huella existing = findById(id);
        Usuario usuario = usuarioService.findById(request.getUsuarioId());
        Actividad actividad = actividadService.findById(request.getActividadId());

        existing.setValor(request.getValor());
        existing.setFecha(request.getFecha());
        existing.setUsuario(usuario);
        existing.setActividad(actividad);
        return huellaRepository.save(existing);
    }

    public void delete(Long id) {
        Huella existing = findById(id);
        huellaRepository.delete(existing);
    }

    public List<ImpactoCategoriaView> calcularImpactoPorCategoria(Long usuarioId) {
        usuarioService.findById(usuarioId);
        return huellaRepository.calcularImpactoPorCategoria(usuarioId);
    }

    public double calcularImpactoTotal(Long usuarioId) {
        return calcularImpactoPorCategoria(usuarioId).stream()
                .mapToDouble(value -> value.getImpacto() == null ? 0.0 : value.getImpacto())
                .sum();
    }

    @Transactional
    public Huella registrarHuellaYActualizarHabito(HuellaRequest request, TipoHabito tipoHabito) {
        Huella huella = create(request);

        HabitoId habitoId = new HabitoId(request.getUsuarioId(), request.getActividadId());
        Habito habito = habitoRepository.findById(habitoId).orElseGet(Habito::new);

        habito.setId(habitoId);
        habito.setUsuario(huella.getUsuario());
        habito.setActividad(huella.getActividad());
        habito.setTipo(tipoHabito);
        habito.setUltimaFecha(huella.getFecha());
        habito.setFechaRegistro(habito.getFechaRegistro() == null ? LocalDate.now() : habito.getFechaRegistro());
        habito.setFrecuencia(habito.getFrecuencia() + 1);

        habitoRepository.save(habito);
        return huella;
    }
}
