package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.recordatorio.IRecordatorio;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioEmail;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioSMS;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioWhatsApp;
import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.jpa.entity.SeguimientoAdopcionJpaEntity;
import com.uade.puppies_tpo.repository.jpa.entity.VisitaJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeguimientoAdopcionMapper {

    private final VisitaMapper visitaMapper;

    public SeguimientoAdopcionMapper(VisitaMapper visitaMapper) {
        this.visitaMapper = visitaMapper;
    }

    public SeguimientoAdopcion toDomain(SeguimientoAdopcionJpaEntity e,
                                         Visitador visitador,
                                         List<Visita> visitas) {
        IRecordatorio preferencia = reconstructPreferencia(e.getPreferenciaRecordatorio());
        SeguimientoAdopcion s = new SeguimientoAdopcion(visitador, e.getCadencia(),
                preferencia, e.getDiasAnticipacion());
        s.setId(e.getId());
        visitas.forEach(s::agregarVisita);
        return s;
    }

    public SeguimientoAdopcionJpaEntity toJpaEntity(SeguimientoAdopcion s,
                                                     List<VisitaJpaEntity> visitaEntities) {
        SeguimientoAdopcionJpaEntity e = new SeguimientoAdopcionJpaEntity();
        e.setId(s.getId());
        e.setVisitadorId(s.getVisitadorAsociado() != null
                ? s.getVisitadorAsociado().obtenerId() : null);
        e.setCadencia(s.getCadencia());
        e.setDiasAnticipacion(s.getDiasAnticipacion());
        if (s.getPreferencia() != null) {
            e.setPreferenciaRecordatorio(s.getPreferencia().getClass().getSimpleName());
        }
        e.setVisitas(visitaEntities);
        return e;
    }

    private IRecordatorio reconstructPreferencia(String nombre) {
        if (nombre == null) return null;
        return switch (nombre) {
            case "RecordatorioSMS"      -> new RecordatorioSMS();
            case "RecordatorioWhatsApp" -> new RecordatorioWhatsApp();
            default                     -> new RecordatorioEmail();
        };
    }
}
