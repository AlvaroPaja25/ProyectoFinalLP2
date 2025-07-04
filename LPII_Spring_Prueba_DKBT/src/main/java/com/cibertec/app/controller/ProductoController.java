package com.cibertec.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.app.entity.Producto;
import com.cibertec.app.service.CategoriaService;
import com.cibertec.app.service.ProductoService;
import com.cibertec.app.service.ProductoProveedorService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProductoProveedorService productoProveedorService;

	@Autowired
	private CategoriaService categoriaService;	

	@GetMapping("/producto")
	public String listProductos(@RequestParam(defaultValue = "vigentes") String filtro, Model model) {
	    List<Producto> productos;

	    if (filtro.equalsIgnoreCase("descontinuados")) {
	        productos = productoService.listarTodosProductosDescontinuados();
	    } else {
	        productos = productoService.listarTodosProductosVigentes();
	    }

	    model.addAttribute("productos", productos);
	    model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
	    model.addAttribute("filtroActual", filtro);
	    return "producto/index";
	}
    
    	@GetMapping("/producto/new")
    	public String createProducto(Model model){	        
    		Producto producto = new Producto();	       
       		model.addAttribute("producto", producto);	        
        	model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());	        
        	return "producto/create";	     
    	}	
	
    	@PostMapping("/producto")
    	public String saveProducto(Producto producto) {	 
    		productoService.guardarProducto(producto);		 
        	return "redirect:/producto";
    	}
	
    	@GetMapping("/producto/edit/{id}")
    	public String editProducto(@PathVariable Long id, Model model) {
       		Producto st = productoService.buscarProductoById(id);	       
	        model.addAttribute("producto", st);	 
	        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());	        
        	return "producto/edit";
    	}	
	
	@PostMapping("/producto/{id}")
	public String updateProducto(@PathVariable Long id, Producto producto, Model model) {
		Producto existentProducto = productoService.buscarProductoById(id);
	    existentProducto.setId(id);
		existentProducto.setNombre(producto.getNombre());
		existentProducto.setDescripcion(producto.getDescripcion());
		existentProducto.setCategoria(producto.getCategoria());
        	productoService.actualizarProducto(existentProducto);	        
	    	return "redirect:/producto";
	}	    
	
    @GetMapping("/producto/{id}")
    public String descontinuarProducto(@PathVariable Long id) {
            productoService.DescontinuarProductoById(id);
            productoProveedorService.deshabilitarRelacionPorProducto(id);
            return "redirect:/producto";
    }
}