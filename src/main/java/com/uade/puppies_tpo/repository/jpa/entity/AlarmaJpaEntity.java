package com.uade.puppies_tpo.repository.jpa.entity;

import com.uade.puppies_tpo.domain.enums.EstadoAlarmaEnum;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "alarmas")
public class AlarmaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_id")
    private Long animalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodicidadAlarmaEnum periodicidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlarmaEnum estado;

    public AlarmaJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public PeriodicidadAlarmaEnum getPeriodicidad() { return periodicidad; }
    public void setPeriodicidad(PeriodicidadAlarmaEnum periodicidad) { this.periodicidad = periodicidad; }
    public EstadoAlarmaEnum getEstado() { return estado; }
    public void setEstado(EstadoAlarmaEnum estado) { this.estado = estado; }
}
