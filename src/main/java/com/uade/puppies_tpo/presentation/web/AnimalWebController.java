package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.dto.AnimalDTO;
import com.uade.puppies_tpo.application.dto.CrearAnimalDTO;
import com.uade.puppies_tpo.application.service.AnimalService;
import com.uade.puppies_tpo.domain.enums.TipoDeAnimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Gestiona la vista web de animales: listado, alta, detalle y transiciones
 * del patron State (iniciar/finalizar tratamiento).
 */
@Controller
@RequestMapping("/animales")
public class AnimalWebController {

    private final AnimalService animalService;

    public AnimalWebController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("animales", animalService.listarTodos());
        return "animales/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("tipos", TipoDeAnimal.values());
        return "animales/nuevo";
    }

    @PostMapping("/nuevo")
    public String crear(@RequestParam String especie,
                        @RequestParam double altura,
                        @RequestParam double peso,
                        @RequestParam int edadAprox,
                        @RequestParam TipoDeAnimal tipoDeAnimal,
                        RedirectAttributes attr) {
        AnimalDTO creado = animalService.registrarAnimal(
                new CrearAnimalDTO(especie, altura, peso, edadAprox, tipoDeAnimal));
        attr.addFlashAttribute("exito", "Animal \"" + creado.nombre() + "\" registrado con id #" + creado.id());
        return "redirect:/animales";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.obtenerAnimal(id));
        return "animales/detalle";
    }

    @PostMapping("/{id}/iniciar-tratamiento")
    public String iniciarTratamiento(@PathVariable Long id, RedirectAttributes attr) {
        try {
            animalService.iniciarTratamiento(id);
            attr.addFlashAttribute("exito", "Tratamiento iniciado. El animal ya no esta disponible para adopcion.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/animales/" + id;
    }

    @PostMapping("/{id}/finalizar-tratamiento")
    public String finalizarTratamiento(@PathVariable Long id, RedirectAttributes attr) {
        try {
            animalService.finalizarTratamiento(id);
            attr.addFlashAttribute("exito", "Tratamiento finalizado. El animal vuelve a estar disponible.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/animales/" + id;
    }

    @PostMapping("/{id}/exportar")
    public String exportarFicha(@PathVariable Long id,
                                @RequestParam String formato,
                                RedirectAttributes attr) {
        try {
            animalService.exportarFicha(id, formato);
            attr.addFlashAttribute("exito", "Ficha exportada en formato " + formato + " (Strategy: " + formato + ").");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/animales/" + id;
    }
}
