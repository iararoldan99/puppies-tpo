package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.FichaAdopcionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaAdopcionSpringDataRepository extends JpaRepository<FichaAdopcionJpaEntity, Long> {}
