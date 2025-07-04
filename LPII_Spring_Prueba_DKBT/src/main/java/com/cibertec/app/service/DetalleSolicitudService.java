package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.DetalleSolicitud;

public interface DetalleSolicitudService {
	
	public List<DetalleSolicitud> listarDetalleSolicitud(Long id);
	
	public void eliminarDetalleSolicitud(Long id);
	
	public void guardarDetalleSolicitud();
}
