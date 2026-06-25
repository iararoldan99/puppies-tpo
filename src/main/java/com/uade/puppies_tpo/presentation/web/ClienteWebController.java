package com.uade.puppies_tpo.presentation.web;

import com.uade.puppies_tpo.application.dto.ClienteDTO;
import com.uade.puppies_tpo.application.dto.CrearClienteDTO;
import com.uade.puppies_tpo.application.service.AdopcionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/** Gestiona el alta y listado de clientes/adoptantes. */
@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    private final AdopcionService adopcionService;

    public ClienteWebController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("clientes", adopcionService.listarClientes());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm() {
        return "clientes/nuevo";
    }

    @PostMapping("/nuevo")
    public String crear(@RequestParam String nombre,
                        @RequestParam(defaultValue = "") String estadoCivil,
                        @RequestParam String email,
                        @RequestParam(defaultValue = "") String telefono,
                        @RequestParam(defaultValue = "OTRO") String ocupacion,
                        @RequestParam(defaultValue = "false") boolean otrasMascotas,
                        @RequestParam(defaultValue = "") String motivoAdopcion,
                        RedirectAttributes attr) {
        ClienteDTO creado = adopcionService.registrarCliente(new CrearClienteDTO(
                nombre, estadoCivil, email, telefono, ocupacion, otrasMascotas,
                motivoAdopcion, List.of()));
        attr.addFlashAttribute("exito", "Cliente \"" + creado.nombre() + "\" registrado con id #" + creado.id());
        return "redirect:/clientes";
    }
}
