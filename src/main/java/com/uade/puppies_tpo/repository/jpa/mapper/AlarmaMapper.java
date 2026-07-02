package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.repository.jpa.entity.AlarmaJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AlarmaMapper {

    public Alarma toDomain(AlarmaJpaEntity e) {
        Alarma a = new Alarma(e.getId(), e.getPeriodicidad());
        a.setAnimalId(e.getAnimalId());
        a.setEstado(e.getEstado());
        return a;
    }

    public AlarmaJpaEntity toJpaEntity(Alarma a) {
        AlarmaJpaEntity e = new AlarmaJpaEntity();
        e.setId(a.getId());
        e.setAnimalId(a.getAnimalId());
        e.setPeriodicidad(a.getPeriodicidad());
        e.setEstado(a.getEstado());
        return e;
    }
}
