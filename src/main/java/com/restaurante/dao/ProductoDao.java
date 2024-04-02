
package com.restaurante.dao;

import com.restaurante.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    //Esta consulta ampliada utiliza metodo query
    public List<Producto>
            findByPrecioBetweenOrderByDescripcion(
                    double precioInf,
                    double precioSup);

    // Esta consulta utiliza leguaje JPQL estas dos cosas valen lo mismo solo que son diferentes
    @Query(value = "SELECT p FROM Producto p where p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")

    public List<Producto> metodoJPQL(
            @Param("precioInf") double precioInf,
            @Param("precioSup") double precioSup);
    
    
    // Esta consulta utiliza lenguaje sql
    @Query(nativeQuery = true,
            value = "SELECT * FROM Producto p where p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")

    public List<Producto> metodoNativo(
            @Param("precioInf") double precioInf,
            @Param("precioSup") double precioSup);


}
