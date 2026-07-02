package com.uade.puppies_tpo.repository.jpa.entity;

import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import jakarta.persistence.*;

@Entity
@Table(name = "animales")
public class AnimalJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String especie;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_animal")
    private TipoDeAnimal tipoDeAnimal;

    @Column
    private double altura;

    @Column
    private double peso;

    @Column(name = "edad_aprox")
    private int edadAprox;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_de_salud")
    private EstadoDeSalud estadoDeSalud;

    @Column(name = "estado_nombre")
    private String estadoNombre;

    public AnimalJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public TipoDeAnimal getTipoDeAnimal() { return tipoDeAnimal; }
    public void setTipoDeAnimal(TipoDeAnimal tipoDeAnimal) { this.tipoDeAnimal = tipoDeAnimal; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public int getEdadAprox() { return edadAprox; }
    public void setEdadAprox(int edadAprox) { this.edadAprox = edadAprox; }
    public EstadoDeSalud getEstadoDeSalud() { return estadoDeSalud; }
    public void setEstadoDeSalud(EstadoDeSalud estadoDeSalud) { this.estadoDeSalud = estadoDeSalud; }
    public String getEstadoNombre() { return estadoNombre; }
    public void setEstadoNombre(String estadoNombre) { this.estadoNombre = estadoNombre; }
}
