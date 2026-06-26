package com.uade.puppies_tpo.domain.alarma;

/**
 * Patron Observer: contrato de quien quiere enterarse cuando una alarma se
 * dispara. Lo implementan los veterinarios, que reciben la "push notification"
 * del enunciado. La alarma (Subject) no conoce implementaciones concretas, solo
 * esta interfaz: agregar o cambiar receptores no la afecta.
 */
public interface ObservadorAlarma {
    void onAlarmaDisparada(Alarma alarma);
}
