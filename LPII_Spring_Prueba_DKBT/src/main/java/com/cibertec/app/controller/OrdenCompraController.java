package com.cibertec.app.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.Empresa;
import com.cibertec.app.entity.OrdenCompra;
import com.cibertec.app.repository.DetalleOrdenCompraRepository;
import com.cibertec.app.repository.EmpresaRepository;
import com.cibertec.app.repository.OrdenCompraRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    
    @Autowired
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @GetMapping("/ordenCompra")
    public String listarOrdenesCompra(Model model) {
        List<OrdenCompra> listaOrdenes = ordenCompraRepository.findAll();
        model.addAttribute("listaOrdenesCompra", listaOrdenes);
        return "ordenCompra/index";
    }
    
    @GetMapping("/ordenCompra/detalle/{id}")
    public String detalleOrdenCompra(@PathVariable Long id, Model model) {
        OrdenCompra orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        
        List<DetalleOrdenCompra> detalles = detalleOrdenCompraRepository.findByOrdenCompraId(id);
        
        model.addAttribute("ordenCompra", orden);
        model.addAttribute("detalles", detalles);
        return "ordenCompra/detalleOrdenCompra";
    }

    @GetMapping("/ordenCompra/pdf/{id}")
    public void exportarOrdenCompraPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
        OrdenCompra orden = ordenCompraRepository.findById(id).orElseThrow();
        Empresa empresa = empresaRepository.findAll().stream().findFirst().orElse(null);
        List<DetalleOrdenCompra> detalles = detalleOrdenCompraRepository.findByOrdenCompraId(id);

        // Preparamos datos como List<Map> para Jasper
        List<Map<String, Object>> detalleDTOs = detalles.stream()
                .map(det -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("producto", det.getProducto().getNombre());
                    map.put("cantidad", det.getCantidad());
                    map.put("precioUnitario", det.getPrecioUnitario());
                    map.put("subTotal", det.getSubTotal());
                    return map;
                })
                .toList();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresaNombreComercial", empresa.getNombreComercial());
        parametros.put("empresaRazonSocial", empresa.getRazonSocial());
        parametros.put("empresaDireccion", empresa.getDireccion());
        parametros.put("empresaRuc", empresa.getRuc());
        parametros.put("empresaCodigoFacturacion", empresa.getCodigoFacturacion());
        parametros.put("ordenId", orden.getId());
        parametros.put("proveedorNombre", orden.getProveedor().getNombre());
        parametros.put("fechaEmision", orden.getSolicitudCompra().getFechaEmision());
        parametros.put("total", orden.getTotal());

        InputStream logoStream = new ClassPathResource("/static/img/logo_empresa.png").getInputStream();
        parametros.put("logo_empresa", logoStream);

        InputStream jasperStream = new ClassPathResource("/reportes/boleta_clinica.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(detalleDTOs);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=orden_compra.pdf");

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }




}
