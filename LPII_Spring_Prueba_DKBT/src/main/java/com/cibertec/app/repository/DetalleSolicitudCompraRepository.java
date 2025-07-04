package com.cibertec.app.repository;

import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.DetalleSolicitudCompraId;
import com.cibertec.app.entity.SolicitudCompra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleSolicitudCompraRepository extends JpaRepository<DetalleSolicitudCompra, DetalleSolicitudCompraId> {
	List<DetalleSolicitudCompra> findBySolicitudCompra(SolicitudCompra solicitudCompra);
}
