package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.adopcion.FichaAdopcion;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.IFichaAdopcionRepository;
import com.uade.puppies_tpo.repository.jpa.entity.FichaAdopcionJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.FichaAdopcionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FichaAdopcionRepositoryJpaImpl implements IFichaAdopcionRepository {

    private final FichaAdopcionSpringDataRepository springRepo;
    private final FichaAdopcionMapper mapper;
    private final IClienteRepository clienteRepository;

    public FichaAdopcionRepositoryJpaImpl(FichaAdopcionSpringDataRepository springRepo,
                                           FichaAdopcionMapper mapper,
                                           IClienteRepository clienteRepository) {
        this.springRepo = springRepo;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public FichaAdopcion save(FichaAdopcion ficha) {
        FichaAdopcionJpaEntity entity = mapper.toJpaEntity(ficha);
        FichaAdopcionJpaEntity saved = springRepo.save(entity);
        ficha.setId(saved.getId());
        return toDomain(saved);
    }

    @Override
    public Optional<FichaAdopcion> findById(Long id) {
        return springRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<FichaAdopcion> findAll() {
        return springRepo.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    private FichaAdopcion toDomain(FichaAdopcionJpaEntity e) {
        Cliente cliente = null;
        if (e.getClienteId() != null) {
            cliente = clienteRepository.findById(e.getClienteId()).orElse(null);
        }
        return mapper.toDomain(e, cliente);
    }
}
