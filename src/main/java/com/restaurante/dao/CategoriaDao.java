
package com.restaurante.dao;

import com.restaurante.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository <Categoria, Long>{
  
}  
  
