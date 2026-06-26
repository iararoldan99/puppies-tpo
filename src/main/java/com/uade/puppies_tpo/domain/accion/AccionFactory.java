package com.uade.puppies_tpo.domain.accion;

/**
 * Crea la accion veterinaria (Command) concreta a partir de su nombre. Centraliza
 * el mapeo nombre -> comando, evitando un switch disperso en los servicios al
 * construir el grupo de acciones de una alarma desde un DTO.
 */
public final class AccionFactory {

    private AccionFactory() {
    }

    public static IAccionVeterinaria porNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("Nombre de accion nulo.");
        }
        String clave = nombre.trim().toUpperCase().replace(" ", "_");
        return switch (clave) {
            case "CONTROL_PARASITOS", "CONTROL_DE_PARASITOS" -> new ControlParasitosAccion();
            case "ANTIPARASITARIOS", "COLOCAR_ANTIPARASITARIOS" -> new ColocarAntiparasitariosAccion();
            case "PESO", "COMPROBAR_PESO" -> new ComprobarPesoAccion();
            case "NUTRICION", "CHEQUEAR_NUTRICION" -> new ChequearNutricionAccion();
            case "VACUNA", "COLOCAR_VACUNA" -> new ColocarVacunaAccion();
            default -> throw new IllegalArgumentException("Accion no reconocida: " + nombre);
        };
    }
}
