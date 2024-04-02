
package com.restaurante.service;

import com.restaurante.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    //Se obtiene un arraylist de objettos tipo Categoria
    public List<Categoria> getCategorias (boolean activos);
    
    // Se obtiene un objeto Categoria apartir del id
    public Categoria getCategoria(Categoria categoria);
    
      
    // Si id tiene un valor se actualiza si no se inicia
    public void saveCategoria(Categoria categoria);
    
    
    // Se elimina un registro de la tabla Categoria apartir del id
    public void deleteCategoria(Categoria categoria);
 
}
