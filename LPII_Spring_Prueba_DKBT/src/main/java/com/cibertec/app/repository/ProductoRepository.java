package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cibertec.app.entity.Producto;

import jakarta.transaction.Transactional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT * FROM producto p WHERE  p.nombre = :nombre", nativeQuery = true)
    public Producto buscarProductoPorNombre(String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.estado = 'Vigente'")
    public List<Producto> listarVigentes();

    @Query("SELECT p FROM Producto p WHERE p.estado = 'Descontinuado'")
    public List<Producto> listarDescontinuados();
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE producto SET estado = 'Descontinuado' WHERE id_producto = :idProducto", nativeQuery = true)
    public void descontinuarProducto(@Param("idProducto") Long idProducto);
       
}