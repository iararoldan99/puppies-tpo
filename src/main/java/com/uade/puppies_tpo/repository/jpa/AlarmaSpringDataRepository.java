package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.AlarmaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmaSpringDataRepository extends JpaRepository<AlarmaJpaEntity, Long> {}
