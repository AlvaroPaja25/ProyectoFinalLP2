package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Proveedor;

import jakarta.transaction.Transactional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	
    @Query(value = "SELECT * FROM proveedor p WHERE  p.nombre = :nombre", nativeQuery = true)
    public Proveedor buscarProveedorPorNombre(String nombre);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedor SET estado = 'Inactivo' WHERE id_proveedor = :idProveedor", nativeQuery = true)
    public void deshabilitarProveedor(@Param("idProveedor") Long idProveedor);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedor SET estado = 'Activo' WHERE id_proveedor = :id", nativeQuery = true)
    public void activarProveedorById(@Param("id") Long id);

    
}