package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.accion.ChequearNutricionAccion;
import com.uade.puppies_tpo.domain.accion.ColocarVacunaAccion;
import com.uade.puppies_tpo.domain.accion.ComprobarPesoAccion;
import com.uade.puppies_tpo.domain.accion.ControlParasitosAccion;
import com.uade.puppies_tpo.domain.accion.GrupoAcciones;
import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.domain.enums.EstadoAccion;
import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Command (acciones uniformes), Composite (grupo de acciones) y Observer
 * (notificacion de alarmas a los veterinarios).
 */
class AlarmaAccionesTest {

    @Test
    void grupoEjecutaTodasLasAcciones() {
        ControlParasitosAccion a1 = new ControlParasitosAccion();
        ComprobarPesoAccion a2 = new ComprobarPesoAccion();
        ChequearNutricionAccion a3 = new ChequearNutricionAccion();

        GrupoAcciones grupo = new GrupoAcciones();
        grupo.agregar(a1);
        grupo.agregar(a2);
        grupo.agregar(a3);
        grupo.ejecutar();

        assertTrue(a1.fueEjecutada());
        assertTrue(a2.fueEjecutada());
        assertTrue(a3.fueEjecutada());
    }

    @Test
    void completarGrupoMarcaTodasComoCompletadas() {
        GrupoAcciones grupo = new GrupoAcciones();
        grupo.agregar(new ColocarVacunaAccion());
        grupo.agregar(new ControlParasitosAccion());

        grupo.ejecutar();
        assertEquals(EstadoAccion.PENDIENTE, grupo.getEstado());

        grupo.completar("Todo en orden");
        assertEquals(EstadoAccion.COMPLETADA, grupo.getEstado());
    }

    @Test
    void dispararEjecutaElGrupoYPasaADisparada() {
        Alarma alarma = new Alarma(10L, PeriodicidadAlarmaEnum.MENSUAL);
        ControlParasitosAccion accion = new ControlParasitosAccion();
        alarma.agregarAccion(accion);

        alarma.disparar();

        assertTrue(accion.fueEjecutada());
        assertEquals(EstadoAlarmaEnum.DISPARADA, alarma.getEstado());
    }

    @Test
    void alDispararseNotificaATodosLosVeterinariosSuscriptos() {
        Alarma alarma = new Alarma(10L, PeriodicidadAlarmaEnum.SEMANAL);
        alarma.agregarAccion(new ComprobarPesoAccion());

        Veterinario vet1 = new Veterinario("v1", "Dra. Paez", "paez@ref.com", "111", "MP-100");
        Veterinario vet2 = new Veterinario("v2", "Dr. Sosa", "sosa@ref.com", "222", "MP-200");
        alarma.suscribir(vet1);
        alarma.suscribir(vet2);

        alarma.disparar();

        assertTrue(vet1.getAlarmasNotificadas().contains(alarma));
        assertTrue(vet2.getAlarmasNotificadas().contains(alarma));
    }

    @Test
    void veterinarioAtiendeAlarmaYCompletaLasAcciones() {
        Alarma alarma = new Alarma(10L, PeriodicidadAlarmaEnum.DIARIA);
        alarma.agregarAccion(new ColocarVacunaAccion());
        alarma.disparar();

        Veterinario vet = new Veterinario("v1", "Dra. Paez", "paez@ref.com", "111", "MP-100");
        vet.atenderAlarma(alarma, "Vacuna aplicada sin reaccion");

        assertEquals(EstadoAccion.COMPLETADA, alarma.getGrupoAcciones().getEstado());
    }
}
