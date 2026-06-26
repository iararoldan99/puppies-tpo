package com.uade.puppies_tpo.repository.inmemory;

import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.IClienteRepository;

public class ClienteRepositoryInMemory extends InMemoryLongRepository<Cliente>
        implements IClienteRepository {

    @Override
    protected Long idDe(Cliente entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Cliente entity, Long id) {
        entity.setId(id);
    }
}
