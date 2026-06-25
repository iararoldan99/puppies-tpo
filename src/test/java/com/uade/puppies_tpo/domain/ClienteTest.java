package com.uade.puppies_tpo.domain;

import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.animal.FichaTecnicaAnimal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.Ocupacion;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Regla: cada cliente puede adoptar un maximo de 2 animales domesticos.
 */
class ClienteTest {

    private Animal domestico(long id) {
        FichaTecnicaAnimal ficha = new FichaTecnicaAnimal(TipoDeAnimal.DOMESTICO, 0.4, 8.0, 3, EstadoDeSalud.SANO);
        return new Animal(id, "Perro" + id, ficha);
    }

    private Cliente cliente() {
        return new Cliente(1L, "Ana Gomez", "Soltera", "ana@mail.com", "1122334455",
                Ocupacion.ESTUDIANTE, false, "Compania");
    }

    @Test
    void puedeAdoptarMientrasTengaMenosDeDos() {
        Cliente cliente = cliente();
        assertTrue(cliente.puedeAdoptar());

        domestico(1L).adoptar(cliente);
        assertTrue(cliente.puedeAdoptar());
    }

    @Test
    void noPuedeAdoptarUnTercerAnimal() {
        Cliente cliente = cliente();
        domestico(1L).adoptar(cliente);
        domestico(2L).adoptar(cliente);

        assertFalse(cliente.puedeAdoptar());
        assertThrows(IllegalStateException.class, () -> domestico(3L).adoptar(cliente));
    }
}
