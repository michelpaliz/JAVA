package com.testing.demo.Models.Controlers;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.testing.demo.Models.Usuario;
import com.testing.demo.Models.interfaces.Servicio.UsuarioServicio;

@Controller
public class ControladorRegistroUsuario {

    @Autowired
    private @Lazy UsuarioServicio servicio;

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {
        modelo.addAttribute("usuarios", servicio.listarUsuario());
        return "index";
    }
}
