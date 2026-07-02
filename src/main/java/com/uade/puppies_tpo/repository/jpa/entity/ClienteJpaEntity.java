package com.uade.puppies_tpo.repository.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class ClienteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column
    private String email;

    @Column
    private String telefono;

    @Column
    private String ocupacion;

    @Column(name = "otras_mascotas")
    private boolean otrasMascotas;

    @Column(name = "motivo_adopcion")
    private String motivoAdopcion;

    @ElementCollection
    @CollectionTable(name = "clientes_animales_adoptados",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "animal_id")
    private List<Long> animalesAdoptadosIds = new ArrayList<>();

    public ClienteJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }
    public boolean isOtrasMascotas() { return otrasMascotas; }
    public void setOtrasMascotas(boolean otrasMascotas) { this.otrasMascotas = otrasMascotas; }
    public String getMotivoAdopcion() { return motivoAdopcion; }
    public void setMotivoAdopcion(String motivoAdopcion) { this.motivoAdopcion = motivoAdopcion; }
    public List<Long> getAnimalesAdoptadosIds() { return animalesAdoptadosIds; }
    public void setAnimalesAdoptadosIds(List<Long> animalesAdoptadosIds) { this.animalesAdoptadosIds = animalesAdoptadosIds; }
}
