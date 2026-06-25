package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.accion.ColocarVacunaAccion;
import com.uade.puppies_tpo.domain.registro.RegistroAccion;
import com.uade.puppies_tpo.domain.registro.RegistroControl;
import com.uade.puppies_tpo.domain.registro.RegistroFactory;
import com.uade.puppies_tpo.domain.registro.RegistroTratamientoMedico;
import com.uade.puppies_tpo.domain.usuario.Veterinario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Factory Method: la fabrica decide que subtipo de registro crear segun sea
 * control de rutina o tratamiento medico.
 */
class RegistroFactoryTest {

    private final Veterinario vet =
            new Veterinario("v1", "Dra. Paez", "paez@ref.com", "111", "MP-100");

    @Test
    void creaRegistroDeTratamientoCuandoEsTratamiento() {
        RegistroAccion registro = RegistroFactory.registrar(
                true, new ColocarVacunaAccion(), "Inicia tratamiento", vet, LocalDate.now());

        assertInstanceOf(RegistroTratamientoMedico.class, registro);
    }

    @Test
    void creaRegistroDeControlCuandoNoEsTratamiento() {
        RegistroAccion registro = RegistroFactory.registrar(
                false, new ColocarVacunaAccion(), "Control mensual", vet, LocalDate.now());

        assertInstanceOf(RegistroControl.class, registro);
        assertEquals("Control mensual", registro.getComentario());
    }
}
