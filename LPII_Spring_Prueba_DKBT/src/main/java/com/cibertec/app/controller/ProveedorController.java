package com.cibertec.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.app.entity.ProductoProveedor;
import com.cibertec.app.entity.ProductoProveedorId;
import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.service.ProductoProveedorService;
import com.cibertec.app.service.ProductoService;
import com.cibertec.app.service.ProveedorService;

@Controller
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@Autowired
	private ProductoProveedorService productoProveedorService;
	
	@Autowired
	private ProductoService productoService;

	@GetMapping("/proveedor")
	public String listarProveedores(Model model) {
		model.addAttribute("proveedores", proveedorService.listarTodosProveedores());
		return "proveedor/index";
	}

	@GetMapping("/proveedor/info/{id}")
	public String listarDetalleSolicitud(Model model, @PathVariable Long id) {
		List<ProductoProveedor> productoProveedor = productoProveedorService.buscarProductoProveedorByProveedorId(id);
		model.addAttribute("productoProveedor", productoProveedor);
		model.addAttribute("idProveedor", id); 
		return "proveedor/info";
	}

	@GetMapping("/proveedor/{id}")
	public String deshabilitarProveedor(Model model, @PathVariable Long id) {
		proveedorService.deshabilitarProveedorById(id);
		productoProveedorService.deshabilitarRelacionPorProveedor(id);
		return "redirect:/proveedor";
	}
	
	
	@GetMapping("/proveedor/edit/{id}")
	public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
	    Proveedor proveedor = proveedorService.obtenerProveedorById(id);
	    if (proveedor == null) {
	        return "redirect:/proveedor?error=notfound";
	    }
	    model.addAttribute("proveedor", proveedor);
	    return "proveedor/edit";
	}

	@PostMapping("/proveedor/update/{id}")
	public String actualizarProveedor(@PathVariable("id") Long id,
	                                  @ModelAttribute("proveedor") Proveedor proveedorActualizado) {
	    Proveedor proveedorExistente = proveedorService.obtenerProveedorById(id);
	    if (proveedorExistente != null) {
	        proveedorExistente.setNombre(proveedorActualizado.getNombre());
	        proveedorExistente.setRuc(proveedorActualizado.getRuc());
	        proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
	        proveedorExistente.setTelefono(proveedorActualizado.getTelefono());
	        proveedorService.guardarProveedor(proveedorExistente);
	    }
	    return "redirect:/proveedor";
	}

	@GetMapping("/proveedor/new")
	public String mostrarFormularioNuevoProveedor(Model model) {
	    model.addAttribute("proveedor", new Proveedor());
	    return "proveedor/create";
	}

	@PostMapping("/proveedor")
	public String guardarNuevoProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
	    proveedorService.guardarProveedor(proveedor);
	    return "redirect:/proveedor?success";
	}
	
	
	@GetMapping("/proveedor/producto")
	public String deshabilitarRelacionProveedorProducto(@RequestParam("idProducto") Long idProducto,
            @RequestParam("idProveedor") Long idProveedor, RedirectAttributes redirectAttributes) {
		
		productoProveedorService.deshabilitarRelacionPorProductoYProveedor(idProveedor, idProducto);
		redirectAttributes.addAttribute("idProveedor", idProveedor);
		
		return "redirect:/proveedor/info/{idProveedor}";
	}
	
	
	@GetMapping("/proveedor/reactivar/{id}")
	public String reactivarProveedor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	    proveedorService.activarProveedorById(id);
	    productoProveedorService.activarRelacionPorProveedor(id);
	    redirectAttributes.addFlashAttribute("success", "Proveedor reactivado correctamente.");
	    return "redirect:/proveedor";
	}

	@GetMapping("/proveedor/producto/reactivar")
	public String reactivarRelacionProveedorProducto(@RequestParam("idProducto") Long idProducto,
	                                                 @RequestParam("idProveedor") Long idProveedor,
	                                                 RedirectAttributes redirectAttributes) {
	    productoProveedorService.activarRelacionPorProductoYProveedor(idProveedor, idProducto);
	    redirectAttributes.addAttribute("idProveedor", idProveedor);
	    return "redirect:/proveedor/info/{idProveedor}";
	}
	
	
	
	
	
	
	
	
	
	
	/*******************************/
	/*@GetMapping("/proveedor/newProducto")
	public String mostrarFormularioNuevoProductoProveedor(@RequestParam("idProveedor") Long idProveedor, Model model) {
	    List<Producto> productos = productoService.listarTodosProductosVigentes();
	    model.addAttribute("productos", productos);
	    model.addAttribute("idProveedor", idProveedor);
	    model.addAttribute("productoProveedor", new ProductoProveedor());
	    return "proveedor/createProvProd";
	}*/
	@GetMapping("/proveedor/newProducto")
	public String mostrarFormularioNuevaRelacion(@RequestParam("idProveedor") Long idProveedor, Model model) {
	    ProductoProveedor relacion = new ProductoProveedor();
	    
	    ProductoProveedorId id = new ProductoProveedorId();
	    id.setProveedor(proveedorService.obtenerProveedorById(idProveedor));
	    relacion.setId(id);

	    model.addAttribute("productoProveedor", relacion);
	    model.addAttribute("listaProductos", productoService.listarTodosProductosVigentes()); // Asegúrate que esta lista esté bien
	    return "proveedor/createProvProd";
	}

	
	@PostMapping("/proveedor/producto")
	public String guardarProductoProveedor(@ModelAttribute("productoProveedor") ProductoProveedor productoProveedor,
	                                       @RequestParam("idProveedor") Long idProveedor,
	                                       @RequestParam("idProducto") Long idProducto,
	                                       RedirectAttributes redirectAttributes) {

	    productoProveedor.setId(new ProductoProveedorId(
	        proveedorService.obtenerProveedorById(idProveedor),
	        productoService.buscarProductoById(idProducto)
	    ));

	    productoProveedorService.guardarProductoProveedor(productoProveedor);

	    redirectAttributes.addAttribute("idProveedor", idProveedor);
	    return "redirect:/proveedor/info/{idProveedor}";
	}

	
	@GetMapping("/proveedor/producto/edit")
	public String mostrarEditarRelacion(@RequestParam("idProducto") Long idProducto,
	                                    @RequestParam("idProveedor") Long idProveedor,
	                                    Model model) {

	    ProductoProveedor rel = productoProveedorService.obtenerRelacion(idProducto, idProveedor);
	    model.addAttribute("productoProveedor", rel);
	    return "proveedor/editProvProd";
	}
	
	@PostMapping("/proveedor/producto/update")
	public String actualizarRelacion(@ModelAttribute ProductoProveedor productoProveedor, RedirectAttributes redirectAttributes) {
	    productoProveedorService.guardarProductoProveedor(productoProveedor);
	    redirectAttributes.addAttribute("idProveedor", productoProveedor.getId().getProveedor().getId());
	    return "redirect:/proveedor/info/{idProveedor}";
	}
	
	@PostMapping("/proveedor/producto/save")
	public String guardarRelacion(@ModelAttribute ProductoProveedor productoProveedor, RedirectAttributes redirectAttributes) {
	    productoProveedorService.guardarProductoProveedor(productoProveedor);
	    redirectAttributes.addAttribute("idProveedor", productoProveedor.getId().getProveedor().getId());
	    return "redirect:/proveedor/info/{idProveedor}";
	}
}
