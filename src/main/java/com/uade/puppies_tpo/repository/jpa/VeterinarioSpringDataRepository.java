package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.repository.jpa.entity.VeterinarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioSpringDataRepository extends JpaRepository<VeterinarioJpaEntity, String> {}
