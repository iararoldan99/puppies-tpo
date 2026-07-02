package com.uade.puppies_tpo.repository.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fichas_adopcion")
public class FichaAdopcionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "otras_mascotas")
    private boolean otrasMascotas;

    @Column(name = "motivo_adopcion")
    private String motivoAdopcion;

    @ElementCollection
    @CollectionTable(name = "ficha_adopcion_animales", joinColumns = @JoinColumn(name = "ficha_id"))
    @Column(name = "animal_id")
    private List<Long> animalesInteresados = new ArrayList<>();

    public FichaAdopcionJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public boolean isOtrasMascotas() { return otrasMascotas; }
    public void setOtrasMascotas(boolean otrasMascotas) { this.otrasMascotas = otrasMascotas; }
    public String getMotivoAdopcion() { return motivoAdopcion; }
    public void setMotivoAdopcion(String motivoAdopcion) { this.motivoAdopcion = motivoAdopcion; }
    public List<Long> getAnimalesInteresados() { return animalesInteresados; }
    public void setAnimalesInteresados(List<Long> animalesInteresados) { this.animalesInteresados = animalesInteresados; }
}
