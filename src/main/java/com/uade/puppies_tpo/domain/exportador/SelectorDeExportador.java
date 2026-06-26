package com.uade.puppies_tpo.domain.exportador;

/**
 * Selecciona la estrategia de exportacion concreta ({@link IExportador}) segun el
 * formato ("PDF" / "Excel"). NO es un Factory: no crea una jerarquia propia, solo
 * elige cual de las estrategias del patron Strategy usar. Centraliza esa unica
 * decision en un lugar para no dispersar el switch en la capa de servicios.
 */
public final class SelectorDeExportador {

    private SelectorDeExportador() {
    }

    public static IExportador porFormato(String formato) {
        if (formato == null) {
            throw new IllegalArgumentException("Formato de exportacion nulo.");
        }
        return switch (formato.trim().toUpperCase()) {
            case "PDF" -> new ExportaPDF();
            case "EXCEL" -> new ExportaExcel();
            default -> throw new IllegalArgumentException("Formato no soportado: " + formato);
        };
    }
}
