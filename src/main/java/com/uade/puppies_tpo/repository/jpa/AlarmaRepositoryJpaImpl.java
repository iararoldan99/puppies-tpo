package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.repository.IAlarmaRepository;
import com.uade.puppies_tpo.repository.jpa.entity.AlarmaJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.AlarmaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlarmaRepositoryJpaImpl implements IAlarmaRepository {

    private final AlarmaSpringDataRepository springRepo;
    private final AlarmaMapper mapper;

    public AlarmaRepositoryJpaImpl(AlarmaSpringDataRepository springRepo, AlarmaMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Alarma save(Alarma alarma) {
        AlarmaJpaEntity entity = mapper.toJpaEntity(alarma);
        AlarmaJpaEntity saved = springRepo.save(entity);
        alarma.setId(saved.getId());
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Alarma> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Alarma> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
