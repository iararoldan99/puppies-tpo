package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.IVisitadorRepository;
import com.uade.puppies_tpo.repository.jpa.entity.VisitadorJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.VisitadorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VisitadorRepositoryJpaImpl implements IVisitadorRepository {

    private final VisitadorSpringDataRepository springRepo;
    private final VisitadorMapper mapper;

    public VisitadorRepositoryJpaImpl(VisitadorSpringDataRepository springRepo, VisitadorMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Visitador save(Visitador v) {
        VisitadorJpaEntity entity = mapper.toJpaEntity(v);
        return mapper.toDomain(springRepo.save(entity));
    }

    @Override
    public Optional<Visitador> findById(String id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Visitador> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(String id) {
        springRepo.deleteById(id);
    }
}
