package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.jpa.entity.VisitaJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VisitaMapper {

    public Visita toDomain(VisitaJpaEntity e, Cliente cliente) {
        Visita v = new Visita(cliente, e.getFecha(), e.getRangoHorario());
        v.setId(e.getId());
        if (e.getEncuestaFecha() != null) {
            v.setEncuesta(new Encuesta(e.getEncuestaFecha(), e.getEncuestaEstadoAnimal(),
                    e.getEncuestaLimpieza(), e.getEncuestaAmbiente(),
                    Boolean.TRUE.equals(e.getEncuestaContinuarVisitas())));
            v.finalizar();
        }
        return v;
    }

    public VisitaJpaEntity toJpaEntity(Visita v) {
        VisitaJpaEntity e = new VisitaJpaEntity();
        e.setId(v.getId());
        e.setClienteId(v.getCliente() != null ? v.getCliente().getId() : null);
        e.setFecha(v.getFecha());
        e.setRangoHorario(v.getRangoHorario());
        if (v.getEncuesta() != null) {
            Encuesta enc = v.getEncuesta();
            e.setEncuestaFecha(enc.getFecha());
            e.setEncuestaEstadoAnimal(enc.getEstadoAnimal());
            e.setEncuestaLimpieza(enc.getLimpieza());
            e.setEncuestaAmbiente(enc.getAmbiente());
            e.setEncuestaContinuarVisitas(enc.isContinuarVisitas());
        }
        return e;
    }
}
