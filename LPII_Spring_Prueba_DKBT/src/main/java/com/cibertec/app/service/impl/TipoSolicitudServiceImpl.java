package com.cibertec.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.app.entity.TipoSolicitud;
import com.cibertec.app.repository.TipoSolicitudRepository;
import com.cibertec.app.service.TipoSolicitudService;

@Service
public class TipoSolicitudServiceImpl implements TipoSolicitudService {

    private TipoSolicitudRepository tipoSolicitudRepository;

    public TipoSolicitudServiceImpl(TipoSolicitudRepository tipoSolicitudRepository) {
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    @Override
    public TipoSolicitud guardarTipoSolicitud(TipoSolicitud tipoSolicitud) {
        return tipoSolicitudRepository.save(tipoSolicitud);
    }

    @Override
    public List<TipoSolicitud> listarTodosTipoSolicitud() {
        return tipoSolicitudRepository.findAll();
    }
}
