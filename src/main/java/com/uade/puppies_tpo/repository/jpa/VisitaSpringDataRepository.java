package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.VisitaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaSpringDataRepository extends JpaRepository<VisitaJpaEntity, Long> {}
