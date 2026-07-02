package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.jpa.entity.VisitadorJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VisitadorMapper {

    public Visitador toDomain(VisitadorJpaEntity e) {
        return new Visitador(e.getId(), e.getNombre(), e.getEmail(),
                e.getTelefono(), e.getZona());
    }

    public VisitadorJpaEntity toJpaEntity(Visitador v) {
        VisitadorJpaEntity e = new VisitadorJpaEntity();
        e.setId(v.obtenerId());
        e.setNombre(v.getNombre());
        e.setEmail(v.getEmail());
        e.setTelefono(v.getTelefono());
        e.setZona(v.getZona());
        return e;
    }
}
