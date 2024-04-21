package com.restaurante.service;

import com.restaurante.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Usuario getUsuario(Usuario usuario);

    Usuario getUsuarioPorUsername(String username);

    Usuario getUsuarioPorUsernameYPassword(String username, String password);

    Usuario getUsuarioPorUsernameOCorreo(String username, String correo);

    boolean existeUsuarioPorUsernameOCorreo(String username, String correo);

    void save(Usuario usuario, boolean crearRolUser);

    void delete(Usuario usuario);
}
