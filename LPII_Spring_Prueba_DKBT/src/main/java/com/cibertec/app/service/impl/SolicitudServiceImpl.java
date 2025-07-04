package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Solicitud;
import com.cibertec.app.repository.SolicitudRepository;
import com.cibertec.app.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Solicitud guardarSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> listarTodasSolicitud() {
        return solicitudRepository.listarOrdenadoPorEstado();
    }

	@Override
	public void eliminarSolicitud(Long id) {
		solicitudRepository.deleteById(id);
	}

	@Override
	public void guardarTodoSolicitud(Solicitud solicitud) {
		solicitudRepository.save(solicitud);
	}

	@Override
	public List<Solicitud> listarTodasSolicitudNoRechazadas() {
		return solicitudRepository.listarSolicitudesNoRechazadas();
	}

	@Override
	public void rechazarSolicitudEstd(Long id) {
		String estado = "Rechazado";
		solicitudRepository.cambiarEstadoSolicitud(id, estado);
	}

	@Override
	public void aprobarSolicitudEstd(Long id) {
		String estado = "Aprobado";
		solicitudRepository.cambiarEstadoSolicitud(id, estado);
	}

	@Override
	public void recibirSolicitudEstd(Long id) {
		String estado = "Recibida";
		solicitudRepository.cambiarEstadoSolicitud(id, estado);
	}

	@Override
	public void pendienteSolicitudEstd(Long id) {
		String estado = "Pendiente";
		solicitudRepository.cambiarEstadoSolicitud(id, estado);
	}
}
