package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.VisitadorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitadorSpringDataRepository extends JpaRepository<VisitadorJpaEntity, String> {}
