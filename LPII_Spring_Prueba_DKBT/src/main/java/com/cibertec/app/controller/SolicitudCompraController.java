package com.cibertec.app.controller;

import com.cibertec.app.entity.SolicitudCompra;
import com.cibertec.app.dto.DetalleSolicitudDTO;
import com.cibertec.app.dto.ProveedorPrecioDTO;
import com.cibertec.app.dto.SolicitudCompraFormDTO;
import com.cibertec.app.entity.Solicitud;
import com.cibertec.app.service.SolicitudService;
import com.cibertec.app.service.SolicitudCompraService;
import com.cibertec.app.service.TipoSolicitudService;
import com.cibertec.app.service.AreaLaboralService;
import com.cibertec.app.service.EmpleadoService;
import com.cibertec.app.service.ProductoProveedorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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




    
}