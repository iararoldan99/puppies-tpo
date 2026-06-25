package com.uade.puppies_tpo.domain.recordatorio;

/**
 * Crea la estrategia de recordatorio segun el canal preferido ("SMS" / "WhatsApp"
 * / "email"), mapeando el dato del DTO a la {@link IRecordatorio} concreta.
 */
public final class RecordatorioFactory {

    private RecordatorioFactory() {
    }

    public static IRecordatorio porCanal(String canal) {
        if (canal == null) {
            throw new IllegalArgumentException("Canal de recordatorio nulo.");
        }
        return switch (canal.trim().toUpperCase()) {
            case "SMS" -> new RecordatorioSMS();
            case "WHATSAPP" -> new RecordatorioWhatsApp();
            case "EMAIL" -> new RecordatorioEmail();
            default -> throw new IllegalArgumentException("Canal no soportado: " + canal);
        };
    }
}
