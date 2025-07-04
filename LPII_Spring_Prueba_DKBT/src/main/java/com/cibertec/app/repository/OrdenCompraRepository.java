package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.app.entity.OrdenCompra;
import com.cibertec.app.entity.SolicitudCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

	List<OrdenCompra> findBySolicitudCompra(SolicitudCompra solicitud);
    // Puedes agregar métodos personalizados aquí si necesitas

}
