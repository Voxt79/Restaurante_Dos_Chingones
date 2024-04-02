package com.restaurante.controller;

import com.restaurante.domain.Producto;
import com.restaurante.service.CategoriaService;
import com.restaurante.service.ProductoService;
import com.restaurante.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller // va a controlar todo lo que pasa en producto
@RequestMapping("/producto") // todos estos metodos van a esta bajo producto
public class ProductoController {

    @Autowired
    private ProductoService productoService; // esto es para poder utilizar producto service
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(true);// crea una variable productos que recupera todo lo que esta dentro de producto
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size()); //Muestra la cantidad de objetos que hay en producto

        // esto agrega al select de productos las categorias
        var categorias = categoriaService.getCategorias(true);// crea una variable productos que recupera todo lo que esta dentro de producto
        model.addAttribute("categorias", categorias);
        return "/producto/listado"; //ubicacion del html
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageImpl;

    @PostMapping("/guardar") //esto es para configurar guardar
    public String guardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { //aqui se gurada la foto
            productoService.saveProducto(producto);
            producto.setRutaImagen(firebaseStorageImpl.cargaImagen(imagenFile, "producto",
                    producto.getIdProducto())); // esto define una ruta en el firebase

        }
        productoService.saveProducto(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String elimina(Producto producto) {
        productoService.deleteProducto(producto);
        return "redirect:/producto/listado";

    }

    @GetMapping("/modificar/{idProducto}")
    public String modifica(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";

    }

   
}
