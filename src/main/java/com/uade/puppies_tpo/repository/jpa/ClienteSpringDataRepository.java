package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteSpringDataRepository extends JpaRepository<ClienteJpaEntity, Long> {}
