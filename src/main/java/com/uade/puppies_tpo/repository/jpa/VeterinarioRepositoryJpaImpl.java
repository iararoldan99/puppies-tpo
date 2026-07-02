package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.usuario.Veterinario;
import com.uade.puppies_tpo.repository.IVeterinarioRepository;
import com.uade.puppies_tpo.repository.jpa.entity.VeterinarioJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.VeterinarioMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VeterinarioRepositoryJpaImpl implements IVeterinarioRepository {

    private final VeterinarioSpringDataRepository springRepo;
    private final VeterinarioMapper mapper;

    public VeterinarioRepositoryJpaImpl(VeterinarioSpringDataRepository springRepo, VeterinarioMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Veterinario save(Veterinario v) {
        VeterinarioJpaEntity entity = mapper.toJpaEntity(v);
        return mapper.toDomain(springRepo.save(entity));
    }

    @Override
    public Optional<Veterinario> findById(String id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Veterinario> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(String id) {
        springRepo.deleteById(id);
    }
}
