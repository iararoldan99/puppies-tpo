package com.uade.puppies_tpo.repository.inmemory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Base para repositorios in-memory de entidades con identificador {@code Long}:
 * genera el id automaticamente al guardar si la entidad aun no lo tiene (como
 * haria una secuencia de base de datos).
 */
public abstract class InMemoryLongRepository<T> extends InMemoryRepository<T, Long> {

    private final AtomicLong secuencia = new AtomicLong(0);

    /** Asigna el id generado a la entidad. */
    protected abstract void asignarId(T entity, Long id);

    @Override
    public T save(T entity) {
        if (idDe(entity) == null) {
            asignarId(entity, secuencia.incrementAndGet());
        }
        return super.save(entity);
    }
}
