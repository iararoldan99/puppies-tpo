package com.uade.puppies_tpo.domain.adopcion;

import com.uade.puppies_tpo.domain.enums.CalificacionEnum;

import java.time.LocalDate;

public class Encuesta {

    private LocalDate fecha;
    private CalificacionEnum estadoAnimal;
    private CalificacionEnum limpieza;
    private CalificacionEnum ambiente;
    private boolean continuarVisitas;

    public Encuesta(LocalDate fecha, CalificacionEnum estadoAnimal, CalificacionEnum limpieza,
                    CalificacionEnum ambiente, boolean continuarVisitas) {
        this.fecha = fecha;
        this.estadoAnimal = estadoAnimal;
        this.limpieza = limpieza;
        this.ambiente = ambiente;
        this.continuarVisitas = continuarVisitas;
    }

    public boolean esEvaluacionPositiva() {
        return estadoAnimal != CalificacionEnum.MALO
                && limpieza != CalificacionEnum.MALO
                && ambiente != CalificacionEnum.MALO;
    }

    public boolean debeMantenerVisitas() {
        return continuarVisitas || !esEvaluacionPositiva();
    }

    public LocalDate getFecha() { return fecha; }
    public CalificacionEnum getEstadoAnimal() { return estadoAnimal; }
    public CalificacionEnum getLimpieza() { return limpieza; }
    public CalificacionEnum getAmbiente() { return ambiente; }
    public boolean isContinuarVisitas() { return continuarVisitas; }
}
