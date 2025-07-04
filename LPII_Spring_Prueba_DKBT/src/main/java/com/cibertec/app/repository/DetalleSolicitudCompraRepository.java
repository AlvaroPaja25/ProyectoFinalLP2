package com.cibertec.app.repository;

import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.DetalleSolicitudCompraId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleSolicitudCompraRepository extends JpaRepository<DetalleSolicitudCompra, DetalleSolicitudCompraId> {
    // Puedes añadir métodos personalizados aquí si lo necesitas
}
