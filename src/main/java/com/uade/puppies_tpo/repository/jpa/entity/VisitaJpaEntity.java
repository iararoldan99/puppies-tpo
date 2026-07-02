package com.uade.puppies_tpo.repository.jpa.entity;

import com.uade.puppies_tpo.domain.enums.CalificacionEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visitas")
public class VisitaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "rango_horario")
    private String rangoHorario;

    @Column(name = "encuesta_fecha")
    private LocalDate encuestaFecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "encuesta_estado_animal")
    private CalificacionEnum encuestaEstadoAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "encuesta_limpieza")
    private CalificacionEnum encuestaLimpieza;

    @Enumerated(EnumType.STRING)
    @Column(name = "encuesta_ambiente")
    private CalificacionEnum encuestaAmbiente;

    @Column(name = "encuesta_continuar_visitas")
    private Boolean encuestaContinuarVisitas;

    public VisitaJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getRangoHorario() { return rangoHorario; }
    public void setRangoHorario(String rangoHorario) { this.rangoHorario = rangoHorario; }
    public LocalDate getEncuestaFecha() { return encuestaFecha; }
    public void setEncuestaFecha(LocalDate encuestaFecha) { this.encuestaFecha = encuestaFecha; }
    public CalificacionEnum getEncuestaEstadoAnimal() { return encuestaEstadoAnimal; }
    public void setEncuestaEstadoAnimal(CalificacionEnum encuestaEstadoAnimal) { this.encuestaEstadoAnimal = encuestaEstadoAnimal; }
    public CalificacionEnum getEncuestaLimpieza() { return encuestaLimpieza; }
    public void setEncuestaLimpieza(CalificacionEnum encuestaLimpieza) { this.encuestaLimpieza = encuestaLimpieza; }
    public CalificacionEnum getEncuestaAmbiente() { return encuestaAmbiente; }
    public void setEncuestaAmbiente(CalificacionEnum encuestaAmbiente) { this.encuestaAmbiente = encuestaAmbiente; }
    public Boolean getEncuestaContinuarVisitas() { return encuestaContinuarVisitas; }
    public void setEncuestaContinuarVisitas(Boolean encuestaContinuarVisitas) { this.encuestaContinuarVisitas = encuestaContinuarVisitas; }
}
