package com.restaurante.service.impl;

import com.restaurante.dao.CategoriaDao;
import com.restaurante.domain.Categoria;
import com.restaurante.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // esta clase es un servicio 
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired //No van a ver más de una copia no consume memoria
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDao.findAll();
        if (activos) { // solo los que estan activos
            lista.removeIf(c -> !c.isActivo()); //remueva todos los que no estan activos
        }
        return categoriaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void saveCategoria(Categoria categoria) {
        categoriaDao.save(categoria);

    }

    @Override
    @Transactional(readOnly = true)

    public void deleteCategoria(Categoria categoria) {
        categoriaDao.delete(categoria);

    }
    
}
