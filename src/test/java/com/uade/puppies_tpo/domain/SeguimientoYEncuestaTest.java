package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.adopcion.Encuesta;
import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.enums.CalificacionEnum;
import com.uade.puppies_tpo.domain.enums.Ocupacion;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioWhatsApp;
import com.uade.puppies_tpo.domain.usuario.Visitador;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Encuesta de la visita y Strategy del canal de recordatorio en el seguimiento.
 */
class SeguimientoYEncuestaTest {

    private Cliente cliente() {
        return new Cliente(1L, "Ana", "Soltera", "ana@mail.com", "111",
                Ocupacion.OTRO, false, "Compania");
    }

    @Test
    void encuestaPositivaCuandoNingunaPreguntaEsMala() {
        Encuesta encuesta = new Encuesta(LocalDate.now(), CalificacionEnum.BUENO,
                CalificacionEnum.REGULAR, CalificacionEnum.BUENO, false);

        assertTrue(encuesta.esEvaluacionPositiva());
    }

    @Test
    void encuestaNegativaCuandoAlgunaEsMala() {
        Encuesta encuesta = new Encuesta(LocalDate.now(), CalificacionEnum.MALO,
                CalificacionEnum.BUENO, CalificacionEnum.BUENO, false);

        assertFalse(encuesta.esEvaluacionPositiva());
        // Evaluacion no positiva => conviene mantener las visitas.
        assertTrue(encuesta.debeMantenerVisitas());
    }

    @Test
    void visitaFinalizadaTomaLaDecisionDeLaEncuesta() {
        Visita visita = new Visita(cliente(), LocalDate.now(), "10-12hs");
        Encuesta encuesta = new Encuesta(LocalDate.now(), CalificacionEnum.BUENO,
                CalificacionEnum.BUENO, CalificacionEnum.BUENO, true);
        visita.setEncuesta(encuesta);

        visita.finalizar();

        assertTrue(visita.isContinuar());
    }

    @Test
    void seguimientoEnviaRecordatorioPorElCanalPreferido() {
        Visitador visitador = new Visitador("vis1", "Leo", "leo@ref.com", "111", "CABA");
        RecordatorioWhatsApp whatsApp = new RecordatorioWhatsApp();
        SeguimientoAdopcion seguimiento =
                new SeguimientoAdopcion(visitador, "Quincenal", whatsApp, 2);

        seguimiento.enviarRecordatorio("Visita el viernes");

        assertEquals(1, whatsApp.getEnviados().size());
        assertEquals("Visita el viernes", whatsApp.getEnviados().get(0));
        assertEquals("WhatsApp", whatsApp.getCanal());
    }
}
