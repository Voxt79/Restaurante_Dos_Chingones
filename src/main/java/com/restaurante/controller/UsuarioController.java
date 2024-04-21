package com.restaurante.controller;

import com.restaurante.domain.Usuario;
import com.restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario) {

        //Validar si es una creacion o modificacion (Si trae un ID)
        boolean nuevo = true;
        if (usuario.getIdUsuario() != 0) {
            nuevo = false;
            Usuario actual = usuarioService.getUsuario(usuario);
            usuario.setPassword(actual.getPassword());
            usuario.setUsername(actual.getUsername());
            usuario.setRoles(actual.getRoles());
        } else {
        }
        usuarioService.save(usuario, nuevo);
        return "/login";
    }
}
