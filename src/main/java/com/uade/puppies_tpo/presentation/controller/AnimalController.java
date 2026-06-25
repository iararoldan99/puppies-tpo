package com.uade.puppies_tpo.presentation.controller;

import com.uade.puppies_tpo.application.dto.AnimalDTO;
import com.uade.puppies_tpo.application.dto.CrearAnimalDTO;
import com.uade.puppies_tpo.application.service.AnimalService;

/**
 * Recibe las peticiones de animales y delega en el service. No contiene logica
 * de negocio; trabaja con DTOs, nunca con entidades de dominio.
 */
public class AnimalController {

    private final AnimalService servicio;

    public AnimalController(AnimalService servicio) {
        this.servicio = servicio;
    }

    public AnimalDTO crear(CrearAnimalDTO dto) {
        return servicio.registrarAnimal(dto);
    }

    public AnimalDTO obtener(Long id) {
        return servicio.obtenerAnimal(id);
    }

    public void exportarFicha(Long id, String formato) {
        servicio.exportarFicha(id, formato);
    }
}
