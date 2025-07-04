package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.app.entity.DetalleSolicitud;
import com.cibertec.app.repository.DetalleSolicitudRepository;
import com.cibertec.app.service.DetalleSolicitudService;

@Service
public class DetalleSolicitudServiceImpl implements DetalleSolicitudService{
	
	private DetalleSolicitudRepository detalleSolicitudRepository;

    public DetalleSolicitudServiceImpl(DetalleSolicitudRepository detalleSolicitudRepository) {
        this.detalleSolicitudRepository = detalleSolicitudRepository;
    }

	@Override
	public List<DetalleSolicitud> listarDetalleSolicitud(Long id) {
		return detalleSolicitudRepository.listarDetalleSolicitudPorId(id);
	}

	@Override
	public void eliminarDetalleSolicitud(Long id) {
		detalleSolicitudRepository.deleteById(id);
	}

	@Override
	public void guardarDetalleSolicitud() {
		// TODO Auto-generated method stub
		
	}

}
