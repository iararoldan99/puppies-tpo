package com.uade.puppies_tpo.domain.exportador;

import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;

/**
 * Estrategia concreta: exporta la ficha a formato PDF.
 */
public class ExportaPDF implements IExportador {

    @Override
    public String exportar(FichaTecnicaAnimal fichaTecnica) {
        return "[PDF] Ficha tecnica - tipo=" + fichaTecnica.getTipoDeAnimal()
                + ", salud=" + fichaTecnica.getEstadoDeSalud()
                + ", peso=" + fichaTecnica.getPeso()
                + ", altura=" + fichaTecnica.getAltura();
    }
}
