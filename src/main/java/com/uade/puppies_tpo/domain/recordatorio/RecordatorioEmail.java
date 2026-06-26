package com.uade.puppies_tpo.domain.recordatorio;

/** Canal concreto: recordatorio por email. */
public class RecordatorioEmail extends RecordatorioBase {

    @Override
    public String getCanal() {
        return "Email";
    }
}
