package com.uade.puppies_tpo.domain.animal;

import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import com.uade.puppies_tpo.domain.estado.EstadoAnimal;
import com.uade.puppies_tpo.domain.estado.EstadoDisponible;
import com.uade.puppies_tpo.domain.estado.EstadoEnTratamiento;

/**
 * Animal ingresado al refugio. Es el contexto del patron State: delega en su
 * {@link EstadoAnimal} actual las reglas del ciclo de adoptabilidad y las
 * transiciones, sin resolverlas el mismo (GRASP: Experto en informacion sobre su
 * propio estado).
 */
public class Animal {

    private Long id;
    private String nombre;
    private FichaTecnicaAnimal fichaTecnica;
    private EstadoAnimal estado;

    public Animal(Long id, String nombre, FichaTecnicaAnimal fichaTecnica) {
        this.id = id;
        this.nombre = nombre;
        this.fichaTecnica = fichaTecnica;
        this.estado = new EstadoDisponible();
    }

    /**
     * Un animal es adoptable solo si es DOMESTICO y, ademas, su estado del ciclo
     * lo permite. Asi un salvaje (aunque este "disponible" en el ciclo) nunca es
     * adoptable, y un domestico en tratamiento tampoco.
     */
    public boolean puedeSerAdoptado() {
        return fichaTecnica != null
                && fichaTecnica.getTipoDeAnimal() == TipoDeAnimal.DOMESTICO
                && estado.permiteAdopcion();
    }

    public void iniciarTratamiento() {
        estado.iniciarTratamiento(this);
    }

    public void finalizarTratamiento() {
        estado.finalizarTratamiento(this);
    }

    public void adoptar(Cliente cliente) {
        if (!puedeSerAdoptado()) {
            throw new IllegalStateException(
                    "El animal '" + nombre + "' no esta en condiciones de ser adoptado.");
        }
        estado.adoptar(this, cliente);
    }

    /** Punto de transicion usado por los estados concretos (patron State). */
    public void cambiarEstado(EstadoAnimal nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public boolean estaEnTratamiento() {
        return estado instanceof EstadoEnTratamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public FichaTecnicaAnimal getFichaTecnica() {
        return fichaTecnica;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }
}
