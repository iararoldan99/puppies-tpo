package com.uade.puppies_tpo.domain.exportador;

import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;

/**
 * Patron Strategy: estrategia de exportacion de la ficha tecnica/medica.
 * La ficha delega el "como" exportar sin conocer el formato concreto, de modo
 * que agregar un formato nuevo solo requiere una nueva implementacion (OCP).
 *
 * Nota: el UML declara {@code exportar(): void}; aca devuelve el contenido
 * generado como String para poder verificarlo en los tests.
 */
public interface IExportador {
    String exportar(FichaTecnicaAnimal fichaTecnica);
}
