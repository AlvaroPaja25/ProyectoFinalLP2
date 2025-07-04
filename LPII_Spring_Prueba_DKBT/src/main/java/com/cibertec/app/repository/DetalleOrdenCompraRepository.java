package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleOrdenCompraId;
import com.cibertec.app.entity.OrdenCompra;

public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, DetalleOrdenCompraId> {
	List<DetalleOrdenCompra> findByOrdenCompraId(Long idOrdenCompra);

	List<DetalleOrdenCompra> findByOrdenCompra(OrdenCompra orden);
}
