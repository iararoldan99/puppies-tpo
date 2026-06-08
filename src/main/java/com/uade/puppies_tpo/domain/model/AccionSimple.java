package com.uade.puppies_tpo.domain.model;

public class AccionSimple implements IAccionVeterinaria {

    private String nombre;
    private String descripcion;
    private boolean completada;
    private String comentarioCompletado;

    public AccionSimple(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completada = false;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando acción: " + nombre + " - " + descripcion);
    }

    @Override
    public void completar(String comentario) {
        this.completada = true;
        this.comentarioCompletado = comentario;
        System.out.println("Acción completada: " + nombre + " | Comentario: " + comentario);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public String getComentarioCompletado() {
        return comentarioCompletado;
    }
}