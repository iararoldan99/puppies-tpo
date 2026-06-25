package com.uade.puppies_tpo.domain.cliente;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.enums.Ocupacion;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente interesado en adoptar. NO es un usuario del sistema: el enunciado
 * aclara que los unicos usuarios son veterinarios y visitadores. Por eso Cliente
 * es una entidad independiente y no hereda de {@code Usuario}.
 *
 * GRASP Experto en informacion: {@link #puedeAdoptar()} vive aca porque el
 * cliente es quien conoce cuantos animales adopto.
 */
public class Cliente {

    /** Maximo de animales domesticos que un cliente puede adoptar. */
    private static final int MAX_ADOPCIONES = 2;

    private Long id;
    private String nombreCompleto;
    private String estadoCivil;
    private String email;
    private String telefono;
    private Ocupacion ocupacion;
    private boolean otrasMascotas;
    private String motivoAdopcion;
    private final List<Animal> animalesAdoptados = new ArrayList<>();

    public Cliente(Long id, String nombreCompleto, String estadoCivil, String email,
                   String telefono, Ocupacion ocupacion, boolean otrasMascotas,
                   String motivoAdopcion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.estadoCivil = estadoCivil;
        this.email = email;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.otrasMascotas = otrasMascotas;
        this.motivoAdopcion = motivoAdopcion;
    }

    /** Cada cliente puede adoptar un maximo de 2 animales domesticos. */
    public boolean puedeAdoptar() {
        return contarDomesticosAdoptados() < MAX_ADOPCIONES;
    }

    public void agregarAnimalAdoptado(Animal animal) {
        animalesAdoptados.add(animal);
    }

    private long contarDomesticosAdoptados() {
        return animalesAdoptados.stream()
                .filter(a -> a.getFichaTecnica() != null
                        && a.getFichaTecnica().getTipoDeAnimal() == TipoDeAnimal.DOMESTICO)
                .count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public boolean tieneOtrasMascotas() {
        return otrasMascotas;
    }

    public String getMotivoAdopcion() {
        return motivoAdopcion;
    }

    public List<Animal> getAnimalesAdoptados() {
        return List.copyOf(animalesAdoptados);
    }
}
