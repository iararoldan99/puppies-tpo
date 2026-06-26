package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.application.dto.AnimalDTO;
import com.uade.puppies_tpo.application.dto.CrearAnimalDTO;
import com.uade.puppies_tpo.application.dto.RegistroAccionDTO;
import com.uade.puppies_tpo.application.mapper.DtoMapper;
import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.exportador.SelectorDeExportador;
import com.uade.puppies_tpo.repository.IAnimalRepository;

import java.util.List;

/**
 * Orquesta los casos de uso de animales. Depende de la abstraccion del
 * repositorio (DIP), recibe y devuelve DTOs y delega las reglas en el dominio.
 */
public class AnimalService {

    private final IAnimalRepository animalRepository;

    public AnimalService(IAnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalDTO> listarTodos() {
        return animalRepository.findAll().stream()
                .map(DtoMapper::toAnimalDTO)
                .toList();
    }

    public AnimalDTO registrarAnimal(CrearAnimalDTO dto) {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal(
                dto.especie(), dto.tipoDeAnimal(), dto.altura(), dto.peso(), dto.edadAprox(),
                EstadoDeSalud.SANO);
        Animal animal = new Animal(null, dto.nombre(), ficha);
        animalRepository.save(animal);
        return DtoMapper.toAnimalDTO(animal);
    }

    public AnimalDTO obtenerAnimal(Long id) {
        return DtoMapper.toAnimalDTO(buscar(id));
    }

    /** Historial clinico del animal (lo que los veterinarios fueron anotando). */
    public List<RegistroAccionDTO> historialDe(Long id) {
        return buscar(id).getFichaTecnica().getHistorialClinico().stream()
                .map(DtoMapper::toRegistroAccionDTO)
                .toList();
    }

    public void iniciarTratamiento(Long animalId) {
        Animal animal = buscar(animalId);
        animal.iniciarTratamiento();
        animalRepository.save(animal);
    }

    public void finalizarTratamiento(Long animalId) {
        Animal animal = buscar(animalId);
        animal.finalizarTratamiento();
        animalRepository.save(animal);
    }

    public void exportarFicha(Long animalId, String formato) {
        Animal animal = buscar(animalId);
        animal.getFichaTecnica().setExportador(SelectorDeExportador.porFormato(formato));
        animal.getFichaTecnica().exportar();
    }

    private Animal buscar(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + id));
    }
}
