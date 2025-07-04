package com.cibertec.app.repository;

import com.cibertec.app.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    // No se necesitan métodos extra para update básico
}

