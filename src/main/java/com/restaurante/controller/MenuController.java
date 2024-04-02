package com.restaurante.controller;

import com.restaurante.domain.Categoria;
import com.restaurante.service.CategoriaService;
import com.restaurante.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // va a controlar todo lo que pasa en categoria
@RequestMapping("/menu") // todos estos metodos van a esta bajo categoria
public class MenuController {

    @Autowired
    private CategoriaService categoriaService; // esto es para poder utilizar categoria service
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return "/menu/listado"; //ubicacion del html
    }

    @GetMapping("/listado/{idCategoria}")
    public String listadoDetalle(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria); //carga el objeto categoria de la base de datos
        var productos = categoria.getProducto(); // obtener la lista de esa categoira
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return "/menu/listado"; //ubicacion del html
        //model.addAttribute("totalProductos",productos.size()); //Muestra la cantidad de objetos que hay en categoria
    }

   
}
