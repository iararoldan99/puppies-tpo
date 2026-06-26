package com.uade.puppies_tpo.domain.recordatorio;

import java.util.ArrayList;
import java.util.List;

/**
 * Base comun de los canales de recordatorio: guarda los mensajes enviados (lo
 * que en un sistema real seria el envio efectivo) para poder verificarlos. Las
 * subclases solo declaran su {@link #getCanal()}.
 */
public abstract class RecordatorioBase implements IRecordatorio {

    private final List<String> enviados = new ArrayList<>();

    public abstract String getCanal();

    @Override
    public void recordar(String mensaje) {
        enviados.add(mensaje);
    }

    public List<String> getEnviados() {
        return List.copyOf(enviados);
    }
}
