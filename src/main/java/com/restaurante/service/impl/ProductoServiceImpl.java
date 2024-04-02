
package com.restaurante.service.impl;

import com.restaurante.dao.ProductoDao;
import com.restaurante.domain.Producto;
import com.restaurante.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // esta clase es un servicio 
public class ProductoServiceImpl implements ProductoService{

    @Autowired //No van a ver más de una copia no consume memoria
    private ProductoDao productoDao;
    
    @Override
    @Transactional (readOnly=true)
    public List<Producto> getProductos(boolean activos) {
       var lista = productoDao.findAll();
        if (activos){ // solo los que estan activos
        lista.removeIf(c -> !c.isActivo()); //remueva todos los que no estan activos
        }
        return productoDao.findAll();
    }

    @Override
    @Transactional (readOnly=true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()). orElse(null);
        }

    @Override
    @Transactional 
    public void saveProducto(Producto producto) {
        productoDao.save(producto);
        
       }

    @Override
    @Transactional 
    public void deleteProducto(Producto producto) {
        productoDao.delete(producto);
        
        }
    @Override
     @Transactional (readOnly=true)
    
     // Esta consulta ampliada utiliza el metodo Query
    public List<Producto>metodoQuery( double precioInf, double precioSup){
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
            
    // Esta consulta utiliza el metodo de lenguaje JPQL        
    public List<Producto> metodoJPQL(double precioInf, double precioSup){
        return productoDao.metodoJPQL(precioInf, precioSup);
    }
    
    // Esta consulta utiliza lenguaje SQL
    public List<Producto> metodoNativo( double precioInf, double precioSup){
        return productoDao.metodoNativo(precioInf, precioSup);
    }
    
}
