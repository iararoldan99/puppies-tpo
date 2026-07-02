package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.estado.EstadoAdoptado;
import com.uade.puppies_tpo.domain.estado.EstadoDisponible;
import com.uade.puppies_tpo.domain.estado.EstadoEnTratamiento;
import com.uade.puppies_tpo.repository.jpa.entity.AnimalJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal toDomain(AnimalJpaEntity e) {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal(
                e.getEspecie(), e.getTipoDeAnimal(),
                e.getAltura(), e.getPeso(),
                e.getEdadAprox(), e.getEstadoDeSalud());
        Animal animal = new Animal(e.getId(), e.getNombre(), ficha);
        animal.cambiarEstado(switch (e.getEstadoNombre() != null ? e.getEstadoNombre() : "") {
            case "EstadoEnTratamiento" -> new EstadoEnTratamiento();
            case "EstadoAdoptado"      -> new EstadoAdoptado();
            default                    -> new EstadoDisponible();
        });
        return animal;
    }

    public AnimalJpaEntity toJpaEntity(Animal a) {
        AnimalJpaEntity e = new AnimalJpaEntity();
        e.setId(a.getId());
        e.setNombre(a.getNombre());
        if (a.getFichaTecnica() != null) {
            e.setEspecie(a.getFichaTecnica().getEspecie());
            e.setTipoDeAnimal(a.getFichaTecnica().getTipoDeAnimal());
            e.setAltura(a.getFichaTecnica().getAltura());
            e.setPeso(a.getFichaTecnica().getPeso());
            e.setEdadAprox(a.getFichaTecnica().getEdadAprox());
            e.setEstadoDeSalud(a.getFichaTecnica().getEstadoDeSalud());
        }
        if (a.getEstado() != null) {
            e.setEstadoNombre(a.getEstado().getClass().getSimpleName());
        }
        return e;
    }
}
