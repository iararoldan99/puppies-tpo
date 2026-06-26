package com.uade.puppies_tpo.domain.exportador;

import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;

/**
 * Estrategia concreta: exporta la ficha a formato Excel.
 */
public class ExportaExcel implements IExportador {

    @Override
    public String exportar(FichaTecnicaAnimal fichaTecnica) {
        return "[EXCEL] tipo;salud;peso;altura\n"
                + fichaTecnica.getTipoDeAnimal() + ";"
                + fichaTecnica.getEstadoDeSalud() + ";"
                + fichaTecnica.getPeso() + ";"
                + fichaTecnica.getAltura();
    }
}
