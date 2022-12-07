package com.testing.demo.Models.interfaces.Implementacion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.testing.demo.Models.Rol;
import com.testing.demo.Models.Usuario;
import com.testing.demo.Models.DTO.UsuarioRegistroDTO;
import com.testing.demo.Models.interfaces.Repository.UsuarioRepositorio;
import com.testing.demo.Models.interfaces.Servicio.UsuarioServicio;

//habia que anyadir el Service porque no me reconocia el sistema esta configuracion (bean).
@Service
public class UsuarioServicioImp implements UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // podriamos utilizar tambien el Autowired que es lo mismo.
    public UsuarioServicioImp(UsuarioRepositorio usuarioRepositorio) {
        super();
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario guardarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = new Usuario(usuarioRegistroDTO.getNombre(), usuarioRegistroDTO.getApellido(),
                usuarioRegistroDTO.getEmail(), passwordEncoder.encode(
                        usuarioRegistroDTO.getPassword()),
                Arrays.asList(new Rol("Role_user")));
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepositorio.findAll();
    }

    // Me sirve para la busqueda de un usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
