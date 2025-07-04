package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.app.entity.OrdenCompra;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    // Puedes agregar métodos personalizados aquí si necesitas
}
