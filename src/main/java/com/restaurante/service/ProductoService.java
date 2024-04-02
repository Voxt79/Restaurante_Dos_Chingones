
package com.restaurante.service;

import com.restaurante.domain.Producto;
import java.util.List;

public interface ProductoService {
    //Se obtiene un arraylist de objettos tipo Producto
    public List<Producto> getProductos (boolean activos);
    
    // Se obtiene un objeto Producto apartir del id
    public Producto getProducto(Producto producto);
    
      
    // Si id tiene un valor se actualiza si no se inicia
    public void saveProducto(Producto producto);
    
    
    // Se elimina un registro de la tabla Producto apartir del id
    public void deleteProducto(Producto producto);
    
    // Esta consulta ampliada utiliza el metodo Query
    public List<Producto>
            metodoQuery( double precioInf, double precioSup);
            
    // Esta consulta utiliza el metodo de lenguaje JPQL        
    public List<Producto> metodoJPQL(double precioInf, double precioSup);
    
    // Esta consulta utiliza lenguaje SQL
    public List<Producto> metodoNativo( double precioInf, double precioSup);
    
    
}
