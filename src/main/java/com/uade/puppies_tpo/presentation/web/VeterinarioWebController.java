package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.dto.CrearVeterinarioDTO;
import com.uade.puppies_tpo.application.dto.VeterinarioDTO;
import com.uade.puppies_tpo.application.service.VeterinarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Gestiona veterinarios. La columna "Alarmas recibidas" en el listado
 * demuestra el patron Observer: el numero sube cada vez que se dispara una alarma.
 */
@Controller
@RequestMapping("/veterinarios")
public class VeterinarioWebController {

    private final VeterinarioService veterinarioService;

    public VeterinarioWebController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("veterinarios", veterinarioService.listarTodos());
        return "veterinarios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm() {
        return "veterinarios/nuevo";
    }

    @PostMapping("/nuevo")
    public String crear(@RequestParam String id,
                        @RequestParam String nombre,
                        @RequestParam String email,
                        @RequestParam(defaultValue = "") String telefono,
                        @RequestParam String matricula,
                        RedirectAttributes attr) {
        try {
            VeterinarioDTO creado = veterinarioService.registrar(
                    new CrearVeterinarioDTO(id, nombre, email, telefono, matricula));
            attr.addFlashAttribute("exito", "Veterinario \"" + creado.nombre() + "\" registrado.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/veterinarios";
    }
}
