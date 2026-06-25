package com.uade.puppies_tpo.domain.animal;

import com.uade.puppies_tpo.domain.alarma.Alarma;
import com.uade.puppies_tpo.domain.enums.EstadoDeSalud;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import com.uade.puppies_tpo.domain.exportador.IExportador;
import com.uade.puppies_tpo.domain.registro.RegistroAccion;

import java.util.ArrayList;
import java.util.List;

/**
 * Ficha tecnica/medica del animal. Vive y se compone dentro del animal.
 *
 * Concentra los datos del ingreso, las alarmas configuradas, el historial
 * clinico y la estrategia de exportacion. (Nota de diseño: es la clase de mayor
 * carga del dominio; si se buscara mayor cohesion, el historial clinico podria
 * extraerse a su propia clase. Se mantiene unificado aqui por fidelidad al
 * modelo.)
 */
public class FichaTecnicaAnimal {

    private TipoDeAnimal tipoDeAnimal;
    private double altura;
    private double peso;
    private int edadAprox;
    private EstadoDeSalud estadoDeSalud;
    private final List<Alarma> alarmas = new ArrayList<>();
    private final List<RegistroAccion> historialClinico = new ArrayList<>();
    private IExportador exportador;

    public FichaTecnicaAnimal(TipoDeAnimal tipoDeAnimal, double altura, double peso,
                              int edadAprox, EstadoDeSalud estadoDeSalud) {
        this.tipoDeAnimal = tipoDeAnimal;
        this.altura = altura;
        this.peso = peso;
        this.edadAprox = edadAprox;
        this.estadoDeSalud = estadoDeSalud;
    }

    /** Strategy: delega el "como" exportar en la estrategia configurada. */
    public String exportar() {
        if (exportador == null) {
            throw new IllegalStateException("No se configuro una estrategia de exportacion.");
        }
        return exportador.exportar(this);
    }

    /** GRASP Creador: la ficha agrega sus propias alarmas y registros. */
    public void agregarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public void agregarRegistro(RegistroAccion registro) {
        historialClinico.add(registro);
    }

    public TipoDeAnimal getTipoDeAnimal() {
        return tipoDeAnimal;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public int getEdadAprox() {
        return edadAprox;
    }

    public EstadoDeSalud getEstadoDeSalud() {
        return estadoDeSalud;
    }

    public void setEstadoDeSalud(EstadoDeSalud estadoDeSalud) {
        this.estadoDeSalud = estadoDeSalud;
    }

    public List<Alarma> getAlarmas() {
        return List.copyOf(alarmas);
    }

    public List<RegistroAccion> getHistorialClinico() {
        return List.copyOf(historialClinico);
    }

    public IExportador getExportador() {
        return exportador;
    }

    public void setExportador(IExportador exportador) {
        this.exportador = exportador;
    }
}
