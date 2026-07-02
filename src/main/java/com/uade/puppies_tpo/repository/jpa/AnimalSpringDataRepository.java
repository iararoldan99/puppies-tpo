package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.AnimalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalSpringDataRepository extends JpaRepository<AnimalJpaEntity, Long> {}
