package com.uade.puppies_tpo.domain.recordatorio;

/** Canal concreto: recordatorio por WhatsApp. */
public class RecordatorioWhatsApp extends RecordatorioBase {

    @Override
    public String getCanal() {
        return "WhatsApp";
    }
}
