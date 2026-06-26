package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.dto.CrearFichaAdopcionDTO;
import com.uade.puppies_tpo.application.dto.FichaAdopcionDTO;
import com.uade.puppies_tpo.application.service.AdopcionService;
import com.uade.puppies_tpo.application.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Gestiona el flujo de adopcion: seleccionar cliente + animal adoptable y
 * procesar la adopcion. El dominio (Estado + Cliente.puedeAdoptar) hace cumplir
 * las reglas; este controller muestra el resultado o el error.
 */
@Controller
@RequestMapping("/adopciones")
public class AdopcionWebController {

    private final AdopcionService adopcionService;
    private final AnimalService animalService;

    public AdopcionWebController(AdopcionService adopcionService, AnimalService animalService) {
        this.adopcionService = adopcionService;
        this.animalService = animalService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("fichas", adopcionService.listarFichas());
        return "adopciones/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("clientes", adopcionService.listarClientes());
        // Solo muestra animales que pueden ser adoptados
        model.addAttribute("animalesAdoptables", animalService.listarTodos().stream()
                .filter(a -> a.puedeSerAdoptado()).toList());
        return "adopciones/nueva";
    }

    @PostMapping("/nueva")
    public String procesar(@RequestParam Long clienteId,
                           @RequestParam Long animalId,
                           RedirectAttributes attr) {
        try {
            FichaAdopcionDTO ficha = adopcionService.procesarAdopcion(
                    new CrearFichaAdopcionDTO(clienteId, animalId));
            attr.addFlashAttribute("exito",
                    "Adopcion procesada. Ficha #" + ficha.id() + " creada para " + ficha.cliente().nombre());
        } catch (IllegalStateException | IllegalArgumentException e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adopciones";
    }
}
