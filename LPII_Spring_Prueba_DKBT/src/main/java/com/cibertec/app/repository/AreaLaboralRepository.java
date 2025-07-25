package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.AreaLaboral;

@Repository
public interface AreaLaboralRepository extends JpaRepository<AreaLaboral, Integer>{

}
