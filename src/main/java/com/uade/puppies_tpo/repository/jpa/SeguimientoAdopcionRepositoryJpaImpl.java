package com.uade.puppies_tpo.repository.jpa;

import com.uade.puppies_tpo.domain.adopcion.SeguimientoAdopcion;
import com.uade.puppies_tpo.domain.adopcion.Visita;
import com.uade.puppies_tpo.domain.usuario.Visitador;
import com.uade.puppies_tpo.repository.ISeguimientoAdopcionRepository;
import com.uade.puppies_tpo.repository.IVisitaRepository;
import com.uade.puppies_tpo.repository.IVisitadorRepository;
import com.uade.puppies_tpo.repository.jpa.entity.SeguimientoAdopcionJpaEntity;
import com.uade.puppies_tpo.repository.jpa.entity.VisitaJpaEntity;
import com.uade.puppies_tpo.repository.jpa.mapper.SeguimientoAdopcionMapper;
import com.uade.puppies_tpo.repository.jpa.mapper.VisitaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SeguimientoAdopcionRepositoryJpaImpl implements ISeguimientoAdopcionRepository {

    private final SeguimientoAdopcionSpringDataRepository springRepo;
    private final SeguimientoAdopcionMapper mapper;
    private final VisitaMapper visitaMapper;
    private final IVisitaRepository visitaRepository;
    private final IVisitadorRepository visitadorRepository;

    public SeguimientoAdopcionRepositoryJpaImpl(SeguimientoAdopcionSpringDataRepository springRepo,
                                                 SeguimientoAdopcionMapper mapper,
                                                 VisitaMapper visitaMapper,
                                                 IVisitaRepository visitaRepository,
                                                 IVisitadorRepository visitadorRepository) {
        this.springRepo = springRepo;
        this.mapper = mapper;
        this.visitaMapper = visitaMapper;
        this.visitaRepository = visitaRepository;
        this.visitadorRepository = visitadorRepository;
    }

    @Override
    public SeguimientoAdopcion save(SeguimientoAdopcion seguimiento) {
        List<VisitaJpaEntity> visitaEntities = seguimiento.getVisitas().stream()
                .map(visitaMapper::toJpaEntity)
                .toList();
        SeguimientoAdopcionJpaEntity entity = mapper.toJpaEntity(seguimiento, visitaEntities);
        SeguimientoAdopcionJpaEntity saved = springRepo.save(entity);
        seguimiento.setId(saved.getId());
        return toDomain(saved);
    }

    @Override
    public Optional<SeguimientoAdopcion> findById(Long id) {
        return springRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<SeguimientoAdopcion> findAll() {
        return springRepo.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    private SeguimientoAdopcion toDomain(SeguimientoAdopcionJpaEntity e) {
        Visitador visitador = null;
        if (e.getVisitadorId() != null) {
            visitador = visitadorRepository.findById(e.getVisitadorId()).orElse(null);
        }
        List<Visita> visitas = e.getVisitas().stream()
                .flatMap(ve -> visitaRepository.findById(ve.getId()).stream())
                .toList();
        return mapper.toDomain(e, visitador, visitas);
    }
}
