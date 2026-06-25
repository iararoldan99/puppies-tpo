# Implementación Java — diferencias respecto del diagrama UML

Este documento acompaña a la implementación del dominio de **Puppies** y explica dónde el código
se aparta a propósito del diagrama de clases (export StarUML), para que ambos queden justificados
ante la cátedra. La fuente de verdad del modelo fue el export StarUML; el código implementa la
**capa de dominio + patrones + interfaces de repositorio**, con **tests unitarios** de los
requerimientos principales (3ª consigna). La persistencia se resuelve con repositorios **in-memory**
(reemplazan a las clases `...RepositoryJDBC` del diagrama; el dominio no nota la diferencia porque
depende solo de la interfaz — DIP).

## Patrones de diseño implementados

| Patrón | Dónde |
|--------|-------|
| **State** | `EstadoAnimal` → `EstadoDisponible` / `EstadoEnTratamiento` / `EstadoAdoptado` |
| **Strategy** (exportación) | `IExportador` → `ExportaPDF` / `ExportaExcel` (+ `ExportadorFactory`) |
| **Strategy** (recordatorio) | `IRecordatorio` → `RecordatorioSMS` / `RecordatorioWhatsApp` / `RecordatorioEmail` |
| **Command** | `IAccionVeterinaria` → las 5 acciones veterinarias |
| **Composite** | `GrupoAcciones implements IAccionVeterinaria` (usado por `Alarma`) |
| **Factory Method** | `RegistroFactory.registrar(...)` (control vs tratamiento) |
| **Repository** | `I*Repository` + implementaciones in-memory |
| **Observer** | `Alarma` (Subject) → `ObservadorAlarma` (lo implementa `Veterinario`) |

## Diferencias respecto del UML (y por qué)

1. **`Cliente` ya no hereda de `Usuario`.** El enunciado indica que los usuarios del sistema son
   solo veterinarios y visitadores; el cliente/adoptante no hace login. `Cliente` quedó como entidad
   independiente y de `Usuario` solo cuelgan `Veterinario` y `Visitador`.

2. **Tipo de animal vs. ciclo de vida (corrección del State).** En el código `TipoDeAnimal`
   (DOMÉSTICO/SALVAJE) es una clasificación **inmutable** de la ficha, y el patrón State modela
   **solo el ciclo** (Disponible/EnTratamiento/Adoptado). Así un **animal salvaje sí puede estar
   en tratamiento** (el refugio ayuda a su recuperación) sin colisionar con el eje de adoptabilidad.
   La regla quedó: `puedeSerAdoptado() = tipo == DOMESTICO && estado.permiteAdopcion()`.

3. **`Alarma` usa el Composite.** La alarma contiene un `GrupoAcciones` como punto de entrada
   (`agregarAccion(...)` / `disparar()`), conectando el Composite con su caso de uso.

4. **Notificación con Observer.** Se eliminó `Veterinario.notificar(alarma)` (responsabilidad
   invertida: el veterinario es el receptor, no el emisor). Ahora `Alarma` es el Subject y notifica
   a los `Veterinario` suscriptos al dispararse.

5. **`RegistroFactory` reincorporado.** El Factory Method que decide entre `RegistroControl` y
   `RegistroTratamientoMedico` figura justificado en el documento de patrones; se reincorporó porque
   lo requiere el historial clínico.

6. **Tipado más fuerte (bad smells corregidos):**
   - `adoptar(animal, cliente)` recibe `Cliente` (no `Object`).
   - `FichaTecnicaAnimal.estadoDeSalud` es enum `EstadoDeSalud` (no `String`).
   - `Alarma.estado` es `EstadoAlarmaEnum` (se corrigió el tipo colgante `EstadoAlarma`).
   - `Cliente.ocupacion` es enum `Ocupacion` (no `String`).
   - `FichaAdopcion.animalesInteresados` es `List<Long>` (no `String`).
   - Se unificó el uso de `boolean` primitivo y `String telefono`.

7. **Detalles menores de implementación.** `IExportador.exportar(...)` y
   `FichaTecnicaAnimal.exportar()` devuelven `String` (en el UML `void`) para poder verificar el
   resultado en los tests. Se agregó un `id` a `Visita`, `FichaAdopcion` y `SeguimientoAdopcion`
   para poder persistirlas. La consulta de "en tratamiento" se ubicó en `Animal` (donde vive el
   estado) en lugar de en la ficha.

## Cómo correr los tests

```
.\mvnw.cmd test      # Windows
./mvnw test          # Linux/Mac
```

26 tests cubren: reglas de adopción y transiciones de estado (incluido el salvaje en tratamiento),
máximo de 2 adopciones por cliente, ejecución del grupo de acciones y disparo de alarma,
notificación Observer a los veterinarios, Factory de registros, ambas Strategies (export y
recordatorio), lógica de la encuesta y el contrato CRUD del repositorio.
