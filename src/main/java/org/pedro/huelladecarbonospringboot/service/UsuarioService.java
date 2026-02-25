package org.pedro.huelladecarbonospringboot.service;

import org.pedro.huelladecarbonospringboot.dto.UsuarioRequest;
import org.pedro.huelladecarbonospringboot.model.Usuario;
import org.pedro.huelladecarbonospringboot.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    public Usuario create(UsuarioRequest request) {
        usuarioRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese email");
        });

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setContrasena(request.getContrasena());
        usuario.setFechaRegistro(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioRequest request) {
        Usuario existing = findById(id);

        usuarioRepository.findByEmail(request.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese email");
                });

        existing.setNombre(request.getNombre());
        existing.setEmail(request.getEmail());
        existing.setContrasena(request.getContrasena());
        return usuarioRepository.save(existing);
    }

    public void delete(Long id) {
        Usuario existing = findById(id);
        usuarioRepository.delete(existing);
    }
}
