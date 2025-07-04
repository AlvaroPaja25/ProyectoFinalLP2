package com.cibertec.app.service;

import com.cibertec.app.entity.TipoSolicitud;

import java.util.List;

public interface TipoSolicitudService {
	
    public TipoSolicitud guardarTipoSolicitud(TipoSolicitud tipoSolicitud);
    
    public  List<TipoSolicitud> listarTodosTipoSolicitud();
}
