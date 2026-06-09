package com.uade.puppies_tpo.domain.model;

public class RegistroControl extends RegistroAccion {
    
    public RegistroControl(String comentario, IAccionVeterinaria accionRealizada, Veterinario veterinario) {
        super(comentario, accionRealizada, veterinario);
    }
}