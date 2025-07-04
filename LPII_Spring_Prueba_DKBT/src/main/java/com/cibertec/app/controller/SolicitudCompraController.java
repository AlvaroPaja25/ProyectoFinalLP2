package com.cibertec.app.controller;

import com.cibertec.app.entity.SolicitudCompra;
import com.cibertec.app.repository.DetalleOrdenCompraRepository;
import com.cibertec.app.repository.DetalleSolicitudCompraRepository;
import com.cibertec.app.repository.EmpresaRepository;
import com.cibertec.app.repository.OrdenCompraRepository;
import com.cibertec.app.repository.SolicitudCompraRepository;
import com.cibertec.app.dto.DetalleSolicitudDTO;
import com.cibertec.app.dto.ProveedorPrecioDTO;
import com.cibertec.app.dto.SolicitudCompraFormDTO;
import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleSolicitud;
import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.Empleado;
import com.cibertec.app.entity.Empresa;
import com.cibertec.app.entity.OrdenCompra;
import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.entity.Solicitud;
import com.cibertec.app.service.SolicitudService;
import com.cibertec.app.service.SolicitudCompraService;
import com.cibertec.app.service.TipoSolicitudService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import com.cibertec.app.service.AreaLaboralService;
import com.cibertec.app.service.EmpleadoService;
import com.cibertec.app.service.ProductoProveedorService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SolicitudCompraController {

    @Autowired
    private SolicitudService solicitudService;
    
    @Autowired
    private SolicitudCompraRepository solicitudCompraRepository;
    
    @Autowired
    private DetalleSolicitudCompraRepository detalleSolicitudCompraRepository;
    
    
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    
    @Autowired
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;
    
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private ProductoProveedorService productoProveedorService;

    
    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private TipoSolicitudService tipoSolicitudService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private AreaLaboralService areaLaboralService;

    // Listado
    @GetMapping("/solicitudCompra")
    public String listSolicitudesCompra(Model model) { 
        List<SolicitudCompra> listaSolicitudesCompra = solicitudCompraService.listarTodos();
        model.addAttribute("listaSolicitudesCompra", listaSolicitudesCompra);        
        return "solicitudCompra/index";
    }
    
    @GetMapping("/solicitudCompra/admin")
    public String listSolicitudesCompraAdmin(Model model) { 
        List<SolicitudCompra> listaSolicitudesCompra = solicitudCompraService.listarTodos();
        model.addAttribute("listaSolicitudesCompra", listaSolicitudesCompra);        
        return "solicitudCompra/admin";
    }
    
    @GetMapping("/solicitudCompra/new")
    public String mostrarFormulario(
            @RequestParam(name = "idSolicitudOriginal", required = false) Long idSolicitudOriginal,
            Model model) {

        List<DetalleSolicitudDTO> productos = solicitudCompraService.obtenerProductosReabastecimiento();

        // Usar el DTO, no la entidad
        model.addAttribute("solicitudCompra", new SolicitudCompraFormDTO());
        model.addAttribute("productos", productos);
        model.addAttribute("idSolicitudOriginal", idSolicitudOriginal);

        return "solicitudCompra/create";
    }

    @PostMapping("/solicitudCompra/save")
    public String guardarSolicitud(@ModelAttribute("solicitudCompra") SolicitudCompraFormDTO dto) {
        solicitudCompraService.guardarSolicitudConOrdenes(dto);
        return "redirect:/solicitudCompra?exito";
    }
    
    @GetMapping("/solicitud_compra/pdf/{id}")
    public void exportarSolicitudCompraPdf(@PathVariable Long id,
                                           HttpServletResponse response) throws Exception {
        // 1) Trae la solicitud compra
        SolicitudCompra solicitudCompra = solicitudCompraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: " + id));

        // 2) Trae todos sus DetalleSolicitudCompra
        List<DetalleSolicitudCompra> detallesSolicitudCompra =
                detalleSolicitudCompraRepository.findBySolicitudCompra(solicitudCompra);

        // 3) Recorre cada solicitud original
        List<Map<String, Object>> listaFinal = new ArrayList<>();

        for (DetalleSolicitudCompra dsc : detallesSolicitudCompra) {
            Solicitud solicitud = dsc.getSolicitud();

            // Recorre sus detalles (productos)
            for (DetalleSolicitud detalleProducto : solicitud.getDetalles()) {
                Map<String, Object> row = new HashMap<>();
                row.put("solicitudId", solicitud.getId());
                row.put("tipoSolicitud", solicitud.getTipoSolicitud().getDescripcion());
                row.put("fechaOriginal", solicitud.getFechaEmision());
                row.put("productoNombre", detalleProducto.getProducto().getNombre());
                row.put("cantidad", detalleProducto.getCantidad());
                listaFinal.add(row);
            }
        }

        // 4) Trae datos de empresa
        Empresa empresa = empresaRepository.findAll().stream().findFirst().orElseThrow();

        // 5) Parámetros del reporte
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresaNombreComercial", empresa.getNombreComercial());
        parametros.put("empresaRazonSocial", empresa.getRazonSocial());
        parametros.put("empresaDireccion", empresa.getDireccion());
        parametros.put("empresaRuc", empresa.getRuc());
        parametros.put("solicitudId", solicitudCompra.getId());
        parametros.put("fechaEmision", solicitudCompra.getFechaEmision());
        parametros.put("estado", solicitudCompra.getEstadoOrden().name());
        parametros.put("observaciones", solicitudCompra.getObservaciones());
        parametros.put("totalGasto", solicitudCompra.getTotalGasto());

        InputStream logoStream = new ClassPathResource("static/img/logo_empresa.png").getInputStream();
        parametros.put("logo_empresa", logoStream);

        // 6) Cargar o compilar .jasper
        InputStream jasperStream = new ClassPathResource("reportes/reabastecimiento_solicitud_compra.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // 7) Llenado del reporte
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaFinal);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        // 8) Exportar PDF al navegador
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=reabastecimiento_solicitud_compra_" + id + ".pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
    
    @GetMapping("/solicitud_compra/detalles/{id}")
    public void exportarSolicitudCompraConOrdenes(
            @PathVariable Long id,
            HttpServletResponse response) throws Exception {

        // 1) Obtener la solicitud de compra
        SolicitudCompra solicitud = solicitudCompraRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: " + id));

        // 2) Traer todas las órdenes de compra asociadas
        List<OrdenCompra> ordenes = ordenCompraRepository.findBySolicitudCompra(solicitud);

        // 3) Crear lista para Jasper
        List<Map<String, Object>> listaFinal = new ArrayList<>();

        for (OrdenCompra orden : ordenes) {
            Proveedor proveedor = orden.getProveedor();
            List<DetalleOrdenCompra> detalles = detalleOrdenCompraRepository.findByOrdenCompra(orden);

            for (DetalleOrdenCompra det : detalles) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("solicitudId", solicitud.getId());
                fila.put("fechaSolicitud", solicitud.getFechaEmision());
                fila.put("estado", solicitud.getEstadoOrden().name());
                fila.put("observaciones", solicitud.getObservaciones());

                fila.put("ordenId", orden.getId());
                fila.put("totalOrden", orden.getTotal());

                fila.put("proveedorNombre", proveedor.getNombre());
                fila.put("proveedorRuc", proveedor.getRuc());

                fila.put("productoNombre", det.getProducto().getNombre());
                fila.put("cantidad", det.getCantidad());
                fila.put("precioUnitario", det.getPrecioUnitario());
                fila.put("subTotal", det.getSubTotal());

                listaFinal.add(fila);
            }
        }

        // 4) Datos empresa
        Empresa empresa = empresaRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("empresaNombreComercial", empresa.getNombreComercial());
        parametros.put("empresaRazonSocial", empresa.getRazonSocial());
        parametros.put("empresaDireccion", empresa.getDireccion());
        parametros.put("empresaRuc", empresa.getRuc());

        InputStream logoStream = new ClassPathResource("static/img/logo_empresa.png").getInputStream();
        parametros.put("logo_empresa", logoStream);

        // ✅ OBTENER EMPLEADO DE LA SOLICITUD
        Empleado empleado = solicitud.getEmpleado();
        parametros.put("empleadoNombre", empleado.getNombres());
        parametros.put("empleadoApellido", empleado.getApellidos());
        parametros.put("empleadoArea", empleado.getArea().getDescripcion());

        // ✅ TOTAL DE GASTO como parámetro (solución a tu error)
        parametros.put("totalGasto", solicitud.getTotalGasto());

        // 5) Cargar reporte
        InputStream jasperStream = new ClassPathResource("reportes/solicitud_ordenes.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // 6) Llenar reporte
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaFinal);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, ds);

        // 7) Exportar
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=solicitud_ordenes_" + id + ".pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }








    
}