package com.uade.puppies_tpo.domain.adopcion;

import com.uade.puppies_tpo.domain.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Ficha que completa el cliente interesado con sus motivos y datos de la
 * adopcion. Los animales interesados se modelan como una lista de ids (en el UML
 * era un String suelto).
 */
public class FichaAdopcion {

    private Long id;
    private Cliente cliente;
    private boolean otrasMascotas;
    private String motivoAdopcion;
    private final List<Long> animalesInteresados = new ArrayList<>();

    public FichaAdopcion(Cliente cliente, boolean otrasMascotas, String motivoAdopcion) {
        this.cliente = cliente;
        this.otrasMascotas = otrasMascotas;
        this.motivoAdopcion = motivoAdopcion;
    }

    public void agregarAnimalInteresado(Long animalId) {
        animalesInteresados.add(animalId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isOtrasMascotas() {
        return otrasMascotas;
    }

    public String getMotivoAdopcion() {
        return motivoAdopcion;
    }

    public List<Long> getAnimalesInteresados() {
        return List.copyOf(animalesInteresados);
    }
}
