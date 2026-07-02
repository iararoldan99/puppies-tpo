package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.repository.jpa.entity.VeterinarioJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VeterinarioMapper {

    public Veterinario toDomain(VeterinarioJpaEntity e) {
        return new Veterinario(e.getId(), e.getNombre(), e.getEmail(),
                e.getTelefono(), e.getMatricula());
    }

    public VeterinarioJpaEntity toJpaEntity(Veterinario v) {
        VeterinarioJpaEntity e = new VeterinarioJpaEntity();
        e.setId(v.obtenerId());
        e.setNombre(v.getNombre());
        e.setEmail(v.getEmail());
        e.setTelefono(v.getTelefono());
        e.setMatricula(v.getMatricula());
        return e;
    }
}
