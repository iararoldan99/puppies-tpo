package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.jpa.entity.AnimalJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.AnimalMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepositoryJpaImpl implements IAnimalRepository {

    private final AnimalSpringDataRepository springRepo;
    private final AnimalMapper mapper;

    public AnimalRepositoryJpaImpl(AnimalSpringDataRepository springRepo, AnimalMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Animal save(Animal animal) {
        AnimalJpaEntity entity = mapper.toJpaEntity(animal);
        AnimalJpaEntity saved = springRepo.save(entity);
        animal.setId(saved.getId());
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Animal> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
