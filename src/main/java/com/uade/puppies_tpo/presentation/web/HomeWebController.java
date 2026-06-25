package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.service.AdopcionService;
import com.uade.puppies_tpo.application.service.AlarmaService;
import com.uade.puppies_tpo.application.service.AnimalService;
import com.uade.puppies_tpo.application.service.VeterinarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Dashboard principal — muestra conteos y links a todas las secciones. */
@Controller
public class HomeWebController {

    private final AnimalService animalService;
    private final AdopcionService adopcionService;
    private final AlarmaService alarmaService;
    private final VeterinarioService veterinarioService;

    public HomeWebController(AnimalService animalService,
                             AdopcionService adopcionService,
                             AlarmaService alarmaService,
                             VeterinarioService veterinarioService) {
        this.animalService = animalService;
        this.adopcionService = adopcionService;
        this.alarmaService = alarmaService;
        this.veterinarioService = veterinarioService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalAnimales", animalService.listarTodos().size());
        model.addAttribute("totalClientes", adopcionService.listarClientes().size());
        model.addAttribute("totalAlarmas", alarmaService.listarTodas().size());
        model.addAttribute("totalVeterinarios", veterinarioService.listarTodos().size());

        long adoptables = animalService.listarTodos().stream()
                .filter(a -> a.puedeSerAdoptado()).count();
        model.addAttribute("animalesAdoptables", adoptables);

        return "home";
    }
}
