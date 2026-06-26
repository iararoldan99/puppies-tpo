package com.uade.puppies_tpo.repository;

import java.util.List;
import java.util.Optional;

/**
 * Contrato base de persistencia (patron Repository). Abstrae el acceso a datos
 * como si fuera una coleccion en memoria. Los servicios dependen de esta
 * abstraccion y no de una implementacion concreta (DIP); la tecnologia real
 * (JDBC, in-memory, etc.) se enchufa por debajo.
 *
 * @param <T>  tipo de entidad
 * @param <ID> tipo del identificador
 */
public interface IRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);
}
