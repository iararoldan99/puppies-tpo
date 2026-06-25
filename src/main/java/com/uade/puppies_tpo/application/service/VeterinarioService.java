package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.application.dto.CrearVeterinarioDTO;
import com.uade.puppies_tpo.application.dto.VeterinarioDTO;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;

import java.util.List;

/**
 * Gestiona el alta y consulta de veterinarios. Al dispararse una alarma, el
 * campo {@code alarmasNotificadas} de cada vet se actualiza via Observer, y
 * este service lo expone en el DTO para mostrarlo en la UI.
 */
public class VeterinarioService {

    private final IVeterinarioRepository veterinarioRepository;

    public VeterinarioService(IVeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public VeterinarioDTO registrar(CrearVeterinarioDTO dto) {
        Veterinario vet = new Veterinario(dto.id(), dto.nombre(), dto.email(),
                dto.telefono(), dto.matricula());
        veterinarioRepository.save(vet);
        return toDTO(vet);
    }

    public List<VeterinarioDTO> listarTodos() {
        return veterinarioRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    private VeterinarioDTO toDTO(Veterinario vet) {
        return new VeterinarioDTO(
                vet.obtenerId(),
                vet.getNombre(),
                vet.getEmail(),
                vet.getMatricula(),
                vet.getAlarmasNotificadas().size());
    }
}
