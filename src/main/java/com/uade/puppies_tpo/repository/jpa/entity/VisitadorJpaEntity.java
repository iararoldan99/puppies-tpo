package com.uade.puppies_tpo.repository.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VISITADOR")
public class VisitadorJpaEntity extends UsuarioJpaEntity {

    @Column
    private String zona;

    public VisitadorJpaEntity() {}

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
}
