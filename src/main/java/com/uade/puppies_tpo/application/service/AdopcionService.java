package com.uade.puppies_tpo.application.service;

import com.uade.puppies_tpo.application.dto.ClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearFichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.CrearSeguimientoDTO;
import com.uade.puppies_tpo.application.dto.FichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.SeguimientoAdopcionDTO;
import com.uade.puppies_tpo.application.mapper.DtoMapper;

import java.util.List;
import com.uade.puppies_tpo.domain.adopcion.FichaAdopcion;
import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.animal.Animal;
import com.uade.puppies_tpo.domain.cliente.Cliente;
import com.uade.puppies_tpo.domain.enums.Ocupacion;
import com.uade.puppies_tpo.domain.recordatorio.IRecordatorio;
import com.uade.puppies_tpo.domain.recordatorio.RecordatorioFactory;
import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.IAnimalRepository;
import com.uade.puppies_tpo.repository.IClienteRepository;
import com.uade.puppies_tpo.repository.IFichaAdopcionRepository;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;
import com.uade.puppies_tpo.repository.IVisitadorRepository;

import java.time.LocalDate;

/**
 * Orquesta el alta del cliente, la adopcion propiamente dicha (haciendo cumplir
 * las reglas del dominio) y la configuracion del seguimiento posterior.
 */
public class AdopcionService {

    private final IClienteRepository clienteRepository;
    private final IAnimalRepository animalRepository;
    private final IFichaAdopcionRepository fichaAdopcionRepository;
    private final ISeguimientoAdopcionRepository seguimientoRepository;
    private final IVisitadorRepository visitadorRepository;

    public AdopcionService(IClienteRepository clienteRepository, IAnimalRepository animalRepository,
                           IFichaAdopcionRepository fichaAdopcionRepository,
                           ISeguimientoAdopcionRepository seguimientoRepository,
                           IVisitadorRepository visitadorRepository) {
        this.clienteRepository = clienteRepository;
        this.animalRepository = animalRepository;
        this.fichaAdopcionRepository = fichaAdopcionRepository;
        this.seguimientoRepository = seguimientoRepository;
        this.visitadorRepository = visitadorRepository;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(DtoMapper::toClienteDTO)
                .toList();
    }

    public List<FichaAdopcionDTO> listarFichas() {
        return fichaAdopcionRepository.findAll().stream()
                .map(f -> new FichaAdopcionDTO(
                        f.getId(),
                        DtoMapper.toClienteDTO(f.getCliente()),
                        null,
                        LocalDate.now()))
                .toList();
    }

    public ClienteDTO registrarCliente(CrearClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.nombre(), dto.estadoCivil(), dto.email(),
                dto.telefono(), parseOcupacion(dto.ocupacion()), dto.otrasMascotas(),
                dto.motivoAdopcion());
        clienteRepository.save(cliente);
        return DtoMapper.toClienteDTO(cliente);
    }

    public FichaAdopcionDTO procesarAdopcion(CrearFichaAdopcionDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + dto.clienteId()));
        Animal animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() -> new IllegalArgumentException("Animal no encontrado: " + dto.animalId()));

        // La regla de adoptabilidad la hace cumplir el dominio (State + Cliente).
        animal.adoptar(cliente);
        animalRepository.save(animal);
        clienteRepository.save(cliente);

        FichaAdopcion ficha = new FichaAdopcion(cliente, cliente.tieneOtrasMascotas(),
                cliente.getMotivoAdopcion());
        ficha.agregarAnimalInteresado(animal.getId());
        fichaAdopcionRepository.save(ficha);

        return new FichaAdopcionDTO(ficha.getId(), DtoMapper.toClienteDTO(cliente),
                DtoMapper.toAnimalDTO(animal), LocalDate.now());
    }

    public SeguimientoAdopcionDTO configurarSeguimiento(CrearSeguimientoDTO dto) {
        Visitador visitador = visitadorRepository.findById(String.valueOf(dto.visitadorId()))
                .orElse(null);
        IRecordatorio preferencia = RecordatorioFactory.porCanal(dto.preferencia());
        SeguimientoAdopcion seguimiento = new SeguimientoAdopcion(visitador,
                String.valueOf(dto.cadencia()), preferencia, dto.diasAnticipacion());
        seguimientoRepository.save(seguimiento);
        return DtoMapper.toSeguimientoDTO(seguimiento);
    }

    private Ocupacion parseOcupacion(String valor) {
        if (valor == null) {
            return Ocupacion.OTRO;
        }
        return switch (valor.trim().toUpperCase()) {
            case "EMPLEADO" -> Ocupacion.EMPLEADO;
            case "ESTUDIANTE" -> Ocupacion.ESTUDIANTE;
            default -> Ocupacion.OTRO;
        };
    }
}
