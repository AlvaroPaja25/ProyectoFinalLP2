package com.cibertec.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.app.entity.Empresa;
import com.cibertec.app.repository.EmpresaRepository;
import com.cibertec.app.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public void actualizarEmpresa(Integer id, Empresa empresaForm, MultipartFile logoFile) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(id);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();

            empresa.setNombreComercial(empresaForm.getNombreComercial());
            empresa.setRazonSocial(empresaForm.getRazonSocial());
            empresa.setRubro(empresaForm.getRubro());
            empresa.setDireccion(empresaForm.getDireccion());
            empresa.setRuc(empresaForm.getRuc());
            empresa.setCodigoFacturacion(empresaForm.getCodigoFacturacion());

            if (logoFile != null && !logoFile.isEmpty()) {
                try {
                    String nombreArchivo = "logo_empresa.png";
                    String ruta = new File("src/main/resources/static/img").getAbsolutePath();
                    File destino = new File(ruta + File.separator + nombreArchivo);
                    logoFile.transferTo(destino);

                    empresa.setLogo(nombreArchivo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            empresaRepository.save(empresa);
        }
    }

    
    @Override
    public Empresa obtenerPorId(Integer id) {
        return empresaRepository.findById(id).orElse(null);
    }

}
