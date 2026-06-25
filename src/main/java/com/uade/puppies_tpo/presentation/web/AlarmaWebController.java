package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.dto.AlarmaDTO;
import com.uade.puppies_tpo.application.dto.AtenderAlarmaDTO;
import com.uade.puppies_tpo.application.dto.CrearAlarmaDTO;
import com.uade.puppies_tpo.application.service.AlarmaService;
import com.uade.puppies_tpo.application.service.AnimalService;
import com.uade.puppies_tpo.application.service.VeterinarioService;
import com.uade.puppies_tpo.domain.enums.PeriodicidadAlarmaEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Gestiona alarmas veterinarias. El disparo demuestra el patron Observer:
 * todos los vets son notificados y el conteo aparece actualizado en /veterinarios.
 */
@Controller
@RequestMapping("/alarmas")
public class AlarmaWebController {

    private final AlarmaService alarmaService;
    private final AnimalService animalService;
    private final VeterinarioService veterinarioService;

    public AlarmaWebController(AlarmaService alarmaService,
                               AnimalService animalService,
                               VeterinarioService veterinarioService) {
        this.alarmaService = alarmaService;
        this.animalService = animalService;
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("alarmas", alarmaService.listarTodas());
        model.addAttribute("veterinarios", veterinarioService.listarTodos());
        return "alarmas/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("animales", animalService.listarTodos());
        model.addAttribute("periodicidades", PeriodicidadAlarmaEnum.values());
        model.addAttribute("accionesDisponibles",
                List.of("VACUNA", "PESO", "PARASITOS", "ANTIPARASITARIOS", "NUTRICION"));
        return "alarmas/nueva";
    }

    @PostMapping("/nueva")
    public String crear(@RequestParam Long animalId,
                        @RequestParam PeriodicidadAlarmaEnum periodicidad,
                        @RequestParam List<String> acciones,
                        RedirectAttributes attr) {
        try {
            AlarmaDTO alarma = alarmaService.crearAlarma(
                    new CrearAlarmaDTO(animalId, periodicidad, acciones));
            attr.addFlashAttribute("exito",
                    "Alarma #" + alarma.id() + " creada con " + alarma.acciones().size() + " accion(es).");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alarmas";
    }

    @PostMapping("/{id}/disparar")
    public String disparar(@PathVariable Long id, RedirectAttributes attr) {
        try {
            alarmaService.dispararAlarma(id);
            // Muestra cuantos vets fueron notificados (Observer)
            long notificados = veterinarioService.listarTodos().stream()
                    .filter(v -> v.alarmasNotificadas() > 0).count();
            attr.addFlashAttribute("exito",
                    "Alarma disparada. " + notificados + " veterinario(s) notificados via Observer. " +
                    "Ver /veterinarios para el detalle.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alarmas";
    }

    @PostMapping("/{id}/atender")
    public String atender(@PathVariable Long id,
                          @RequestParam String veterinarioId,
                          @RequestParam(defaultValue = "false") boolean esTratamiento,
                          @RequestParam(defaultValue = "Atendida desde la UI") String comentario,
                          RedirectAttributes attr) {
        try {
            alarmaService.atenderAlarma(id, new AtenderAlarmaDTO(comentario, esTratamiento, veterinarioId));
            String tipoRegistro = esTratamiento ? "tratamiento médico" : "control de rutina";
            attr.addFlashAttribute("exito",
                    "Alarma atendida por el veterinario " + veterinarioId + ". Se anotó un "
                    + tipoRegistro + " en el historial del animal (RegistroFactory / Simple Factory).");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alarmas";
    }
}
