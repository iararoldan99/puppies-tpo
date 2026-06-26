package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.repository.IRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion in-memory generica del contrato {@link IRepository}, respaldada
 * por un mapa. Reemplaza, para fines de prueba, a las implementaciones JDBC del
 * diagrama: el dominio no nota la diferencia porque depende solo de la interfaz.
 */
public abstract class InMemoryRepository<T, ID> implements IRepository<T, ID> {

    protected final Map<ID, T> store = new LinkedHashMap<>();

    /** Extrae el identificador de la entidad. */
    protected abstract ID idDe(T entity);

    @Override
    public T save(T entity) {
        store.put(idDe(entity), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
    }
}
