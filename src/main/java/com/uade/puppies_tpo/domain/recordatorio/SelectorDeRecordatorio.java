package com.uade.puppies_tpo.domain.recordatorio;

/**
 * Selecciona la estrategia de recordatorio concreta ({@link IRecordatorio}) segun
 * el canal preferido ("SMS" / "WhatsApp" / "email"). NO es un Factory: solo elige
 * cual de las estrategias del patron Strategy usar, mapeando el dato del DTO a la
 * implementacion correspondiente.
 */
public final class SelectorDeRecordatorio {

    private SelectorDeRecordatorio() {
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
