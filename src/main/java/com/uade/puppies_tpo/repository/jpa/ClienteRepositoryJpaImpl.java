package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.jpa.entity.ClienteJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.ClienteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryJpaImpl implements IClienteRepository {

    private final ClienteSpringDataRepository springRepo;
    private final ClienteMapper mapper;
    private final IAnimalRepository animalRepository;

    public ClienteRepositoryJpaImpl(ClienteSpringDataRepository springRepo,
                                     ClienteMapper mapper,
                                     IAnimalRepository animalRepository) {
        this.springRepo = springRepo;
        this.mapper = mapper;
        this.animalRepository = animalRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteJpaEntity entity = mapper.toJpaEntity(cliente);
        ClienteJpaEntity saved = springRepo.save(entity);
        cliente.setId(saved.getId());
        return loadWithAnimales(saved);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return springRepo.findById(id).map(this::loadWithAnimales);
    }

    @Override
    public List<Cliente> findAll() {
        return springRepo.findAll().stream().map(this::loadWithAnimales).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    private Cliente loadWithAnimales(ClienteJpaEntity entity) {
        List<Animal> animales = entity.getAnimalesAdoptadosIds().stream()
                .flatMap(animalId -> animalRepository.findById(animalId).stream())
                .toList();
        return mapper.toDomain(entity, animales);
    }
}
