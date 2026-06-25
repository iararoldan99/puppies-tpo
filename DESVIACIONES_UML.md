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
| **Strategy** (exportación) | `IExportador` → `ExportaPDF` / `ExportaExcel` (selección vía `SelectorDeExportador`) |
| **Strategy** (recordatorio) | `IRecordatorio` → `RecordatorioSMS` / `RecordatorioWhatsApp` / `RecordatorioEmail` (selección vía `SelectorDeRecordatorio`) |
| **Command** | `IAccionVeterinaria` → las 5 acciones veterinarias |
| **Composite** | `GrupoAcciones implements IAccionVeterinaria` (usado por `Alarma`) |
| **Simple Factory** | `RegistroFactory.registrar(...)` (control vs tratamiento) — Simple/Static Factory, no el GoF Factory Method |
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

5. **`RegistroFactory` reincorporado.** El **Simple Factory** que decide entre `RegistroControl` y
   `RegistroTratamientoMedico` figura justificado en el documento de patrones; se reincorporó porque
   lo requiere el historial clínico. (Es una Simple/Static Factory, no el GoF Factory Method.)

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

## Capas de aplicación y presentación (Services, DTOs, Controllers)

Se completó la vista del UML con las 3 capas restantes. **No se usa base de datos**: los services
dependen de las interfaces de repositorio y, en ejecución/tests, se enchufan las implementaciones
in-memory.

- **Services** (`application.service`): `AnimalService`, `AlarmaService`, `AdopcionService`,
  `VisitaService`, `NotificacionService`. Orquestan los casos de uso, mapean DTO↔dominio y delegan
  las reglas en el dominio (p. ej. `procesarAdopcion` deja que `Animal`/`Cliente` hagan cumplir la
  regla de adopción).
- **DTOs** (`application.dto`): los 16 del diagrama, implementados como `record`.
- **Controllers** (`presentation.controller`): `AnimalController`, `AlarmaController`,
  `AdopcionController`, `VisitaController`, `NotificacionController`. Solo reciben y delegan en el
  service; trabajan con DTOs.

Diferencias de implementación en estas capas:

- **Controllers y services son clases planas** (sin `@RestController`/`@Service`): el UML no modela
  endpoints REST y el proyecto no incluye `spring-boot-starter-web`. Mantenerlos planos preserva la
  independencia de framework y refleja exactamente las dependencias del diagrama
  (Controller → Service → Interfaz de repositorio).
- **DTOs usan `LocalDate`** donde el UML decía `Date`, por consistencia con el dominio.
- **`CrearAnimalDTO` no tiene `nombre`** (solo `especie`): se usa la especie como nombre del animal.
  En los DTOs de salida `especie` se deriva del `TipoDeAnimal` y `condicionMedica` del `EstadoDeSalud`.
- **Helpers agregados** (no están en el UML, reducen duplicación y switches dispersos):
  `AccionFactory` (Simple Factory que crea el Command de cada acción) y los **selectores de
  estrategia** `SelectorDeExportador` / `SelectorDeRecordatorio` (mapean formato/canal a la
  estrategia Strategy concreta — no son factories porque no crean una jerarquía propia, solo
  eligen cuál de las estrategias existentes usar) y `DtoMapper` (centraliza el mapeo dominio→DTO).
- **Notificación:** `NotificacionService.notificarVeterinarios` recorre los veterinarios del
  repositorio y les avisa (`onAlarmaDisparada`). `AlarmaService.dispararAlarma` dispara la alarma y
  llama al service de notificación.

## Cómo correr los tests

```
.\mvnw.cmd test      # Windows
./mvnw test          # Linux/Mac
```

29 tests cubren: reglas de adopción y transiciones de estado (incluido el salvaje en tratamiento),
máximo de 2 adopciones por cliente, ejecución del grupo de acciones y disparo de alarma,
notificación Observer a los veterinarios, Factory de registros, ambas Strategies (export y
recordatorio), lógica de la encuesta, el contrato CRUD del repositorio y el flujo completo
Controller → Service → Repository in-memory con DTOs.
