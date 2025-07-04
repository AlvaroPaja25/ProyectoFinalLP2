package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.app.entity.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

	@Query(value = "SELECT * FROM solicitud ORDER BY " + "CASE estado_solicitud " + "WHEN 'Rechazado' THEN 0 "
			+ "WHEN 'Pendiente' THEN 1 " + "WHEN 'Aprobado' THEN 2 " + "ELSE 3 END", nativeQuery = true)
	public List<Solicitud> listarOrdenadoPorEstado();

	@Query(value = "SELECT * FROM solicitud WHERE estado_solicitud <> 'Rechazado' ORDER BY " + "CASE estado_solicitud "
			+ "WHEN 'Pendiente' THEN 0 " + "WHEN 'Aprobado' THEN 1 " + "ELSE 2 END", nativeQuery = true)
	public List<Solicitud> listarSolicitudesNoRechazadas();

	@Modifying
	@Transactional
	@Query(value = "UPDATE solicitud s SET s.estado_solicitud = :estado WHERE s.id_solicitud = :id", nativeQuery = true)
	public void cambiarEstadoSolicitud(@Param("id") Long id, @Param("estado") String estado);
	
}
