package com.uade.puppies_tpo.domain.model;

public class RegistroTratamientoMedico extends RegistroAccion {

    private boolean finalizoTratamiento;

    public RegistroTratamientoMedico(String comentario, IAccionVeterinaria accionRealizada, Veterinario veterinario, boolean finalizoTratamiento) {
        super(comentario, accionRealizada, veterinario);
        this.finalizoTratamiento = finalizoTratamiento;
    }

    public boolean isFinalizoTratamiento() {
        return finalizoTratamiento;
    }
    
    
    public void evaluarEstadoClinico(Animal animal) {
        if (this.finalizoTratamiento) {
            animal.finalizarTratamiento(); 
        }
    }
    
    @Override
    public void actualizarFicha(FichaTecnicaAnimal ficha) {
        ficha.setEnTratamientoMedico(!finalizoTratamiento);
    }
}