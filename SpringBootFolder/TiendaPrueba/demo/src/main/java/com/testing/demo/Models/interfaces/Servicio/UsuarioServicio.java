package com.testing.demo.Models.interfaces.Servicio;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.testing.demo.Models.Usuario;
import com.testing.demo.Models.DTO.UsuarioRegistroDTO;

public interface UsuarioServicio extends UserDetailsService {
    public Usuario guardarUsuario(UsuarioRegistroDTO UsuarioRegistroDTO);

    public List<Usuario> listarUsuario();
}
