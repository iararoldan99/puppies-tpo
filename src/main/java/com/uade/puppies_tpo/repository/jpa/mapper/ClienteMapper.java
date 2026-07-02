package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.jpa.entity.ClienteJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public Cliente toDomain(ClienteJpaEntity e, List<Animal> animalesAdoptados) {
        Cliente c = new Cliente(e.getId(), e.getNombreCompleto(), e.getEstadoCivil(),
                e.getEmail(), e.getTelefono(), e.getOcupacion(),
                e.isOtrasMascotas(), e.getMotivoAdopcion());
        animalesAdoptados.forEach(c::agregarAnimalAdoptado);
        return c;
    }

    public ClienteJpaEntity toJpaEntity(Cliente c) {
        ClienteJpaEntity e = new ClienteJpaEntity();
        e.setId(c.getId());
        e.setNombreCompleto(c.getNombreCompleto());
        e.setEstadoCivil(c.getEstadoCivil());
        e.setEmail(c.getEmail());
        e.setTelefono(c.getTelefono());
        e.setOcupacion(c.getOcupacion());
        e.setOtrasMascotas(c.tieneOtrasMascotas());
        e.setMotivoAdopcion(c.getMotivoAdopcion());
        List<Long> ids = c.getAnimalesAdoptados().stream()
                .filter(a -> a.getId() != null)
                .map(Animal::getId)
                .toList();
        e.setAnimalesAdoptadosIds(ids);
        return e;
    }
}
