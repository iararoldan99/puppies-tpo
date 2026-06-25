package com.uade.puppies_tpo.domain.recordatorio;

/**
 * Patron Strategy (segundo uso): canal de recordatorio. El seguimiento guarda la
 * preferencia del cliente (SMS / WhatsApp / email) y le delega el envio, sin
 * hardcodear la logica de cada canal.
 */
public interface IRecordatorio {
    void recordar(String mensaje);
}
