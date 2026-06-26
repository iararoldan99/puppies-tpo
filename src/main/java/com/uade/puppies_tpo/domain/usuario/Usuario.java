package com.uade.puppies_tpo.domain.usuario;

/**
 * Usuario del sistema. El manejo de login/registro lo hace el equipo de
 * seguridad; aca solo guardamos una referencia (el {@code id} es la referencia
 * externa al modulo de autenticacion) y los datos minimos que necesitamos.
 *
 * Solo los veterinarios y visitadores son usuarios del sistema (el cliente no).
 */
public abstract class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String telefono;

    protected Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String obtenerDatos() {
        return nombre + " <" + email + ">";
    }

    public String obtenerId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
}
