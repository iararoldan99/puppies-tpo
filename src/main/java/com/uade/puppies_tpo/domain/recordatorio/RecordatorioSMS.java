package com.uade.puppies_tpo.domain.recordatorio;

/** Canal concreto: recordatorio por SMS. */
public class RecordatorioSMS extends RecordatorioBase {

    @Override
    public String getCanal() {
        return "SMS";
    }
}
