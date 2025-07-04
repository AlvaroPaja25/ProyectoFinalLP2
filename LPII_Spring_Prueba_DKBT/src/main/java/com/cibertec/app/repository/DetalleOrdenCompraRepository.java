package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleOrdenCompraId;

public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, DetalleOrdenCompraId> {
	List<DetalleOrdenCompra> findByOrdenCompraId(Long idOrdenCompra);
}
