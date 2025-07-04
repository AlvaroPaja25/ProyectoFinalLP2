package com.cibertec.app.controller;

import com.cibertec.app.entity.DetalleSolicitud;
import com.cibertec.app.entity.Solicitud;
import com.cibertec.app.service.SolicitudService;
import com.cibertec.app.service.TipoSolicitudService;
import com.cibertec.app.service.AreaLaboralService;
import com.cibertec.app.service.DetalleSolicitudService;
import com.cibertec.app.service.EmpleadoService;
import com.cibertec.app.service.ProductoService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private DetalleSolicitudService detalleSolicitudService;

    @Autowired
    private TipoSolicitudService tipoSolicitudService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private AreaLaboralService areaLaboralService;

    @GetMapping("/solicitud")
    public String listSolicitudes(Model model) {        
        List<Solicitud> solicitudes = solicitudService.listarTodasSolicitud();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("tipoSolicitudList", tipoSolicitudService.listarTodosTipoSolicitud());
        return "solicitud/index";
    }
    
    @GetMapping("/solicitud/info/{id}")
    public String listarDetalleSolicitud(Model model, @PathVariable Long id) {
    	List<DetalleSolicitud> detalleSolicitud = detalleSolicitudService.listarDetalleSolicitud(id);
    	model.addAttribute("detalleSolicitud", detalleSolicitud);
    	return "solicitud/info";
    }
    
    @GetMapping("/solicitud/{id}") 
    public String deleteSolicitud(@PathVariable Long id) {
    	solicitudService.eliminarSolicitud(id);
    	detalleSolicitudService.eliminarDetalleSolicitud(id);
    	return "redirect:/solicitud";
    }
    
    @PostMapping("/solicitud")
    public String guardarSolicitudCompleto(@ModelAttribute Solicitud solicitud) {
        if (solicitud.getDetalles() != null) {
            for (DetalleSolicitud detalle : solicitud.getDetalles()) {
                detalle.setSolicitud(solicitud);
            }
        }

        solicitud.setFechaEmision(new Date());
        solicitudService.guardarTodoSolicitud(solicitud);
        return "redirect:/solicitud";
    }

    @GetMapping("/solicitud/new")
    public String createSolicitudForm(Model model) {
        Solicitud solicitud = new Solicitud();
        DetalleSolicitud detalleSolicitud = new DetalleSolicitud();
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("detalleSolicitud", detalleSolicitud);
        model.addAttribute("tipoSolicitudList", tipoSolicitudService.listarTodosTipoSolicitud());
        model.addAttribute("empleadoList", empleadoService.listarTodosEmpleado());
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("productoList", productoService.listarTodosProductosVigentes());
        return "solicitud/create";
    }
    //Editttttttttttttttttttttttttttttttttttttttttttttttttttttt
    
  /*  @GetMapping("/detalle-solicitud/edit/{id}")
    public String editarDetalleSolicitudForm(@PathVariable Long id, Model model) {
        DetalleSolicitud detalle = detalleSolicitudService.obtenerPorId(id);
        model.addAttribute("detalleSolicitud", detalle);
        model.addAttribute("productoList", productoService.listarTodosProductosVigentes());
        return "detalleSolicitud/edit"; // este será el HTML
    }

    @PostMapping("/detalle-solicitud/save")
    public String guardarDetalleEditado(@ModelAttribute DetalleSolicitud detalle) {
        detalleSolicitudService.g(detalle);
        solicitudService.pendienteSolicitudEstd(detalle.getSolicitud().getId());
        return "redirect:/solicitud/detalle/" + detalle.getSolicitud().getId(); // o a donde desees redirigir
    }
**/
    
    //Contabilidad Administrar Solicitudes de Abastecimineto
    @GetMapping("/solicitud/cont")
    public String listSolicitudesContabilidad(Model model) {        
        List<Solicitud> solicitudes = solicitudService.listarTodasSolicitudNoRechazadas();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("tipoSolicitudList", tipoSolicitudService.listarTodosTipoSolicitud());
        return "solicitud/indexCont";
    }
    
    @GetMapping("/solicitud/rechazar/{id}") 
    public String rechazarSolicitud(@PathVariable Long id) {
    	solicitudService.rechazarSolicitudEstd(id);
    	return "redirect:/solicitud/cont";
    }
    
    @GetMapping("/solicitud/info/cont/{id}")
    public String listarDetalleSolicitudConts(Model model, @PathVariable Long id) {
    	List<DetalleSolicitud> detalleSolicitud = detalleSolicitudService.listarDetalleSolicitud(id);
    	model.addAttribute("detalleSolicitud", detalleSolicitud);
    	return "solicitud/infoCont";
    }

}
