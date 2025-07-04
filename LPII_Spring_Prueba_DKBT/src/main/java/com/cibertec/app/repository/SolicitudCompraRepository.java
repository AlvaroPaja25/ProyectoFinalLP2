package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.app.entity.SolicitudCompra;

public interface SolicitudCompraRepository extends JpaRepository<SolicitudCompra, Long> {
    // Puedes agregar consultas personalizadas si las necesitas
}
