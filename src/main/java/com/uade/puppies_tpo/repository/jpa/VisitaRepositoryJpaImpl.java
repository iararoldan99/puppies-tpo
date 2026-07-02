package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.IVisitaRepository;
import com.uade.puppies_tpo.repository.jpa.entity.VisitaJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.VisitaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VisitaRepositoryJpaImpl implements IVisitaRepository {

    private final VisitaSpringDataRepository springRepo;
    private final VisitaMapper mapper;
    private final IClienteRepository clienteRepository;

    public VisitaRepositoryJpaImpl(VisitaSpringDataRepository springRepo,
                                    VisitaMapper mapper,
                                    IClienteRepository clienteRepository) {
        this.springRepo = springRepo;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Visita save(Visita visita) {
        VisitaJpaEntity entity = mapper.toJpaEntity(visita);
        VisitaJpaEntity saved = springRepo.save(entity);
        visita.setId(saved.getId());
        return toDomain(saved);
    }

    @Override
    public Optional<Visita> findById(Long id) {
        return springRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Visita> findAll() {
        return springRepo.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    private Visita toDomain(VisitaJpaEntity e) {
        Cliente cliente = null;
        if (e.getClienteId() != null) {
            cliente = clienteRepository.findById(e.getClienteId()).orElse(null);
        }
        return mapper.toDomain(e, cliente);
    }
}
