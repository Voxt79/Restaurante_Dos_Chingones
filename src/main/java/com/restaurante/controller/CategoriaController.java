package com.restaurante.controller;

import com.restaurante.domain.Categoria;
import com.restaurante.service.CategoriaService;
import com.restaurante.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller // va a controlar todo lo que pasa en categoria
@RequestMapping("/categoria") // todos estos metodos van a esta bajo categoria
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService; // esto es para poder utilizar categoria service

    @GetMapping("/listado")
    public String listado(Model model) {
        var categorias = categoriaService.getCategorias(true);// crea una variable categorias que recupera todo lo que esta dentro de categoria
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size()); //Muestra la cantidad de objetos que hay en categoria
        return "/categoria/listado"; //ubicacion dek html
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageImpl;

    @PostMapping("/guardar") //esto es para configurar guardar
    public String guardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { //aqui se gurada la foto
            categoriaService.saveCategoria(categoria);
            categoria.setRutaImagen(firebaseStorageImpl.cargaImagen(imagenFile, "categoria",
                    categoria.getIdCategoria())); // esto define una ruta en el firebase

        }
        categoriaService.saveCategoria(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String elimina(Categoria categoria) {
        categoriaService.deleteCategoria(categoria);
        return "redirect:/categoria/listado";

    }
      @GetMapping("/modificar/{idCategoria}")
    public String modifica(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
                model.addAttribute("categoria", categoria);
        return "/categoria/modifica";

    }
  

}
