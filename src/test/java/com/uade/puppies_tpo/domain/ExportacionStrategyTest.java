package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import com.uade.puppies_tpo.domain.exportador.ExportaExcel;
import com.uade.puppies_tpo.domain.exportador.ExportaPDF;
import com.uade.puppies_tpo.domain.exportador.ExportadorFactory;
import com.uade.puppies_tpo.domain.exportador.IExportador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Strategy: la ficha delega la exportacion en la estrategia configurada, que se
 * selecciona por formato mediante la fabrica.
 */
class ExportacionStrategyTest {

    private FichaTecnicaAnimal ficha() {
        return new FichaTecnicaAnimal(TipoDeAnimal.DOMESTICO, 0.4, 8.0, 3, EstadoDeSalud.SANO);
    }

    @Test
    void fabricaDevuelveLaEstrategiaSegunFormato() {
        assertInstanceOf(ExportaPDF.class, ExportadorFactory.porFormato("PDF"));
        assertInstanceOf(ExportaExcel.class, ExportadorFactory.porFormato("excel"));
        assertThrows(IllegalArgumentException.class, () -> ExportadorFactory.porFormato("XML"));
    }

    @Test
    void laFichaDelegaEnLaEstrategiaPDF() {
        FichaTecnicaAnimal ficha = ficha();
        ficha.setExportador(ExportadorFactory.porFormato("PDF"));

        assertTrue(ficha.exportar().startsWith("[PDF]"));
    }

    @Test
    void cambiarLaEstrategiaCambiaElFormatoExportado() {
        FichaTecnicaAnimal ficha = ficha();
        IExportador excel = new ExportaExcel();
        ficha.setExportador(excel);

        assertTrue(ficha.exportar().startsWith("[EXCEL]"));
    }

    @Test
    void exportarSinEstrategiaConfiguradaFalla() {
        assertThrows(IllegalStateException.class, () -> ficha().exportar());
    }
}
