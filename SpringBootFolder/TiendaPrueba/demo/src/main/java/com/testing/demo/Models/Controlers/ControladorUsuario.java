package com.testing.demo.Models.Controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testing.demo.Models.DTO.UsuarioRegistroDTO;
import com.testing.demo.Models.interfaces.Servicio.UsuarioServicio;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/registroUsuario")
public class ControladorUsuario {
    private UsuarioServicio usuarioServicio;

    // se podria usar el @Autowired
    public ControladorUsuario(UsuarioServicio usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarUsuario() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping()
    public String mostrarFormularioRegistro() {
        return "registroUsuario";
    }

    @PostMapping
    public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO usuarioRegistroDTO) {
        usuarioServicio.guardarUsuario(usuarioRegistroDTO);
        return "redirect:/registroUsuario?exito";
    }
}
