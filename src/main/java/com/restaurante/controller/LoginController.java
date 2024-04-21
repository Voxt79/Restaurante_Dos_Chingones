package com.restaurante.controller;

import com.restaurante.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.restaurante.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        // Verificar si el usuario existe en la base de datos
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Usuario encontrado y contraseña correcta
            // Puedes almacenar información del usuario en la sesión si es necesario
            // Redirigir al usuario a la página de inicio
            return "redirect:/index";
        } else {
            // Usuario no encontrado o contraseña incorrecta
            // Agregar un mensaje de error para mostrar en la página de inicio de sesión
            model.addAttribute("error", "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
            return "login"; // Volver a cargar la página de inicio de sesión con un mensaje de error
        }
    }
}
