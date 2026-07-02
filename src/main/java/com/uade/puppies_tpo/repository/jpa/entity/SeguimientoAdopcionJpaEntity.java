package com.uade.puppies_tpo.repository.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seguimientos_adopcion")
public class SeguimientoAdopcionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visitador_id")
    private String visitadorId;

    @Column
    private String cadencia;

    @Column(name = "preferencia_recordatorio")
    private String preferenciaRecordatorio;

    @Column(name = "dias_anticipacion")
    private int diasAnticipacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "seguimientos_adopcion_visitas",
            joinColumns = @JoinColumn(name = "seguimiento_adopcion_id"),
            inverseJoinColumns = @JoinColumn(name = "visitas_id")
    )
    private List<VisitaJpaEntity> visitas = new ArrayList<>();

    public SeguimientoAdopcionJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVisitadorId() { return visitadorId; }
    public void setVisitadorId(String visitadorId) { this.visitadorId = visitadorId; }
    public String getCadencia() { return cadencia; }
    public void setCadencia(String cadencia) { this.cadencia = cadencia; }
    public String getPreferenciaRecordatorio() { return preferenciaRecordatorio; }
    public void setPreferenciaRecordatorio(String preferenciaRecordatorio) { this.preferenciaRecordatorio = preferenciaRecordatorio; }
    public int getDiasAnticipacion() { return diasAnticipacion; }
    public void setDiasAnticipacion(int diasAnticipacion) { this.diasAnticipacion = diasAnticipacion; }
    public List<VisitaJpaEntity> getVisitas() { return visitas; }
    public void setVisitas(List<VisitaJpaEntity> visitas) { this.visitas = visitas; }
}
