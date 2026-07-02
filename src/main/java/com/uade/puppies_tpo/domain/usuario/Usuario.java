package com.uade.puppies_tpo.domain.usuario;

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

    public String obtenerDatos() { return nombre + " <" + email + ">"; }
    public String obtenerId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}
