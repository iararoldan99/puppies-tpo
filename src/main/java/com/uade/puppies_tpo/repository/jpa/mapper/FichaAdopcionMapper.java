package com.uade.puppies_tpo.repository.jpa.mapper;

import com.uade.puppies_tpo.domain.adopcion.FichaAdopcion;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.repository.jpa.entity.FichaAdopcionJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FichaAdopcionMapper {

    public FichaAdopcion toDomain(FichaAdopcionJpaEntity e, Cliente cliente) {
        FichaAdopcion f = new FichaAdopcion(cliente, e.isOtrasMascotas(), e.getMotivoAdopcion());
        f.setId(e.getId());
        e.getAnimalesInteresados().forEach(f::agregarAnimalInteresado);
        return f;
    }

    public FichaAdopcionJpaEntity toJpaEntity(FichaAdopcion f) {
        FichaAdopcionJpaEntity e = new FichaAdopcionJpaEntity();
        e.setId(f.getId());
        e.setClienteId(f.getCliente() != null ? f.getCliente().getId() : null);
        e.setOtrasMascotas(f.isOtrasMascotas());
        e.setMotivoAdopcion(f.getMotivoAdopcion());
        e.setAnimalesInteresados(new ArrayList<>(f.getAnimalesInteresados()));
        return e;
    }
}
