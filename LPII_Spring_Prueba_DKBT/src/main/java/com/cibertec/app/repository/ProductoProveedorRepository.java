package com.cibertec.app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.entity.ProductoProveedor;
import com.cibertec.app.entity.ProductoProveedorId;

@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, ProductoProveedorId> {

	
	@Query(value = "SELECT * FROM producto_proveedor WHERE id_producto = :idProducto AND id_proveedor = :idProveedor", nativeQuery = true)
	public ProductoProveedor obtenerRelacionPorIds(@Param("idProducto") Long idProducto, @Param("idProveedor") Long idProveedor);
	
	@Query(value="SELECT * FROM producto_proveedor pp where pp.id_producto = :id_producto",nativeQuery = true)
	public List<ProductoProveedor> buscarByProductoId(Long id_producto);
	
	@Query(value="SELECT * FROM producto_proveedor pp where pp.id_proveedor = :id_proveedor",nativeQuery = true)
	public List<ProductoProveedor> buscarByProveedorId(Long id_proveedor);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE producto_proveedor SET estado = 'Deshabilitado' WHERE id_producto = :idProducto", nativeQuery = true)
	public void deshabilitarRelacionPorProductoBD(@Param("idProducto") Long idProducto);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE producto_proveedor SET estado = 'Deshabilitado' WHERE id_proveedor = :idProveedor", nativeQuery = true)
	public void deshabilitarRelacionPorProveedorBD(@Param("idProveedor") Long idProveedor);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE producto_proveedor SET estado = 'Deshabilitado' WHERE id_proveedor = :idProveedor AND id_producto = :idProducto", nativeQuery = true)
	public void deshabilitarRelacionPorProveedorYProducto(@Param("idProveedor") Long idProveedor, @Param("idProducto") Long idProducto);
	
	@Query(value = """
		    SELECT 
		        p.id_proveedor,
		        p.nombre,
		        pp.precio
		    FROM producto_proveedor pp
		    JOIN proveedor p ON pp.id_proveedor = p.id_proveedor
		    WHERE pp.id_producto = :idProducto
		      AND pp.estado = 'Habilitado'
		""", nativeQuery = true)
		List<Object[]> listarProveedoresConPrecioPorProducto(@Param("idProducto") Long idProducto);

	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE producto_proveedor SET estado = 'Habilitado' WHERE id_proveedor = :idProveedor", nativeQuery = true)
	public void activarRelacionPorProveedor(@Param("idProveedor") Long idProveedor);

	@Modifying
	@Transactional
	@Query(value = "UPDATE producto_proveedor SET estado = 'Habilitado' WHERE id_proveedor = :idProveedor AND id_producto = :idProducto", nativeQuery = true)
	public void activarRelacionPorProductoYProveedor(@Param("idProveedor") Long idProveedor, @Param("idProducto") Long idProducto);

	
}