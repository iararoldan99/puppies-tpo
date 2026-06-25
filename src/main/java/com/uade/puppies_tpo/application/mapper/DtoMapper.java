package com.uade.puppies_tpo.application.mapper;

import com.uade.puppies_tpo.application.dto.AlarmaDTO;
import com.uade.puppies_tpo.application.dto.AnimalDTO;
import com.uade.puppies_tpo.application.dto.ClienteDTO;
import com.uade.puppies_tpo.application.dto.EncuestaDTO;
import com.uade.puppies_tpo.application.dto.SeguimientoAdopcionDTO;
import com.uade.puppies_tpo.application.dto.VisitaDTO;
import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.recordatorio.IRecordatorio;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioBase;

import java.util.List;

/**
 * Traduce entidades de dominio a DTOs de salida. Centraliza el mapeo para que no
 * se duplique en cada servicio.
 */
public final class DtoMapper {

    private DtoMapper() {
    }

    public static AnimalDTO toAnimalDTO(Animal animal) {
        String ciclo;
        if (animal.estaEnTratamiento()) {
            ciclo = "EN_TRATAMIENTO";
        } else if (animal.getEstado().permiteAdopcion()) {
            ciclo = "DISPONIBLE";
        } else {
            ciclo = "ADOPTADO";
        }
        return new AnimalDTO(
                animal.getId(),
                animal.getNombre(),
                animal.getFichaTecnica().getTipoDeAnimal().name(),
                animal.getFichaTecnica().getEstadoDeSalud().name(),
                animal.puedeSerAdoptado(),
                ciclo);
    }

    public static ClienteDTO toClienteDTO(Cliente cliente) {
        List<Long> idsAdoptados = cliente.getAnimalesAdoptados().stream()
                .map(Animal::getId)
                .toList();
        return new ClienteDTO(cliente.getId(), cliente.getNombreCompleto(),
                cliente.getEmail(), idsAdoptados);
    }

    public static AlarmaDTO toAlarmaDTO(Alarma alarma) {
        List<String> nombresAcciones = alarma.getGrupoAcciones().getAcciones().stream()
                .map(Object::toString)
                .toList();
        return new AlarmaDTO(alarma.getId(), alarma.getPeriodicidad(),
                alarma.getEstado(), nombresAcciones);
    }

    public static SeguimientoAdopcionDTO toSeguimientoDTO(SeguimientoAdopcion seguimiento) {
        String visitador = seguimiento.getVisitadorAsociado() != null
                ? seguimiento.getVisitadorAsociado().getNombre()
                : null;
        return new SeguimientoAdopcionDTO(seguimiento.getId(), visitador,
                parseCadencia(seguimiento.getCadencia()), canalDe(seguimiento.getPreferencia()));
    }

    public static VisitaDTO toVisitaDTO(Visita visita) {
        EncuestaDTO encuesta = visita.getEncuesta() != null ? toEncuestaDTO(visita.getEncuesta()) : null;
        return new VisitaDTO(visita.getId(), visita.getFecha(), encuesta, visita.isContinuar());
    }

    public static EncuestaDTO toEncuestaDTO(Encuesta encuesta) {
        return new EncuestaDTO(encuesta.getEstadoAnimal().name(),
                encuesta.getLimpieza().name(), encuesta.getAmbiente().name());
    }

    private static String canalDe(IRecordatorio recordatorio) {
        return recordatorio instanceof RecordatorioBase base ? base.getCanal() : null;
    }

    private static int parseCadencia(String cadencia) {
        try {
            return cadencia != null ? Integer.parseInt(cadencia.trim()) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
