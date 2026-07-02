package com.uade.puppies_tpo.repository.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VETERINARIO")
public class VeterinarioJpaEntity extends UsuarioJpaEntity {

    @Column
    private String matricula;

    public VeterinarioJpaEntity() {}

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}
