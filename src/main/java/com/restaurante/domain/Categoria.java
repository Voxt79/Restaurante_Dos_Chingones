
package com.restaurante.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="categoria")
public class Categoria implements Serializable{ //los objetos categoria van a ser trasladados a cierto lugar
    private static final long serialVersionUID = 1L; //Si no hay ningun registro empiece por el 1
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tome lo que dice la base de datos que es id categoria y pongalo en id categoria 
    @Column(name="id_categoria") // en la tabla se llama asi pero se va a utilizar de otra manera
    private long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany //Optener el listado de productos de una categoria
    @JoinColumn(name="id_categoria", updatable = false)
    private List<Producto> producto;
    
}
