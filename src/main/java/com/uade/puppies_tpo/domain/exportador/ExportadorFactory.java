package com.uade.puppies_tpo.domain.exportador;

/**
 * Mapea un formato ("PDF" / "Excel") a su estrategia de exportacion concreta.
 * Centraliza la unica decision de "que estrategia usar" en un solo lugar,
 * evitando que ese switch quede disperso en la capa de servicios (bad smell:
 * switch latente).
 */
public final class ExportadorFactory {

    private ExportadorFactory() {
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
