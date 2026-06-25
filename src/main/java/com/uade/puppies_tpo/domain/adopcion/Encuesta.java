package com.uade.puppies_tpo.domain.adopcion;

import com.uade.puppies_tpo.domain.enums.CalificacionEnum;

import java.time.LocalDate;

/**
 * Encuesta que responde el visitador al finalizar una visita. Las tres preguntas
 * usan la misma escala malo/regular/bueno, mas la indicacion de si se debe
 * continuar con las visitas.
 */
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

    /** La evaluacion es positiva si ninguna de las tres preguntas dio MALO. */
    public boolean esEvaluacionPositiva() {
        return estadoAnimal != CalificacionEnum.MALO
                && limpieza != CalificacionEnum.MALO
                && ambiente != CalificacionEnum.MALO;
    }

    /**
     * Se mantienen las visitas si el visitador lo indico explicitamente o si la
     * evaluacion no fue positiva (conviene seguir controlando).
     */
    public boolean debeMantenerVisitas() {
        return continuarVisitas || !esEvaluacionPositiva();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public CalificacionEnum getEstadoAnimal() {
        return estadoAnimal;
    }

    public CalificacionEnum getLimpieza() {
        return limpieza;
    }

    public CalificacionEnum getAmbiente() {
        return ambiente;
    }

    public boolean isContinuarVisitas() {
        return continuarVisitas;
    }
}
