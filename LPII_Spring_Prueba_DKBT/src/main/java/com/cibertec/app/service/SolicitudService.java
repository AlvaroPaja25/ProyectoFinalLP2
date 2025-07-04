package com.cibertec.app.service;

import com.cibertec.app.entity.Solicitud;

import java.util.List;

public interface SolicitudService {
	
    public Solicitud guardarSolicitud(Solicitud solicitud);
    
    public List<Solicitud> listarTodasSolicitud();
    
    public List<Solicitud> listarTodasSolicitudNoRechazadas();
    
    public void eliminarSolicitud(Long id);
    
    public void rechazarSolicitudEstd(Long id);
    
    public void aprobarSolicitudEstd(Long id);
    
    public void recibirSolicitudEstd(Long id);
    
    public void pendienteSolicitudEstd(Long id);
    
    public void guardarTodoSolicitud(Solicitud solicitud);
}
