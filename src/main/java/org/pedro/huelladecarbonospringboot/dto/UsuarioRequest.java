package org.pedro.huelladecarbonospringboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos de entrada para crear o actualizar un usuario")
public class UsuarioRequest {
    @Schema(description = "Nombre completo del usuario", example = "Pedro Nadales")
    @NotBlank
    private String nombre;
    @Schema(description = "Correo electronico unico del usuario", example = "pedro@demo.com")
    @Email
    @NotBlank
    private String email;
    @Schema(description = "Contrasena del usuario", example = "1234")
    @NotBlank
    private String contrasena;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
