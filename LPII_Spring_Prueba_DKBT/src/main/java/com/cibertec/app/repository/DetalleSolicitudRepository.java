package com.cibertec.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.DetalleSolicitud;

@Repository
public interface DetalleSolicitudRepository extends JpaRepository<DetalleSolicitud, Long> {
		
	@Query(value = "SELECT  * FROM detalle_solicitud ds WHERE  ds.id_solicitud = :id", nativeQuery = true)
    public List<DetalleSolicitud> listarDetalleSolicitudPorId(Long id);
	
	@Query(value = """
	        SELECT 
    s.id_solicitud AS idSolicitud,
    ds.id_producto AS idProducto,
    p.nombre AS nombre,
    SUM(ds.cantidad) AS totalCantidad
FROM
    solicitud s
    JOIN detalle_solicitud ds ON s.id_solicitud = ds.id_solicitud
    JOIN producto p ON ds.id_producto = p.id_producto
    JOIN tipo_solicitud ts ON s.id_tipo_solicitud = ts.id_tipo_solicitud
WHERE
    s.estado_solicitud = 'Pendiente'
    AND ts.descripcion = 'Reabastecimiento'
GROUP BY
    s.id_solicitud, ds.id_producto, p.nombre
ORDER BY
    s.id_solicitud, p.id_producto

	        """, nativeQuery = true)
	    List<Object[]> listarProductosAprobadosAgrupados();
}
