package com.ipartek.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
