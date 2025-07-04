package com.cibertec.app.controller;

import com.cibertec.app.entity.Empleado;
import com.cibertec.app.service.AreaLaboralService;
import com.cibertec.app.service.CargoEmpleadoService;
import com.cibertec.app.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private AreaLaboralService areaLaboralService;

    @Autowired
    private CargoEmpleadoService cargoEmpleadoService;

    // Mostrar listado de empleados
    @GetMapping("/empleado")
    public String listEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.listarTodosEmpleado());
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("cargoList", cargoEmpleadoService.listarTodosCargoEmpleado());
        
        return "empleado/index";
    }

    // Mostrar formulario para crear nuevo empleado
    @GetMapping("/empleado/new")
    public String createEmpleadoForm(Model model) {
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("cargoList", cargoEmpleadoService.listarTodosCargoEmpleado());
        
        return "empleado/create";
    }

    
	@PostMapping("/empleado")
	public String saveEmpleado(Empleado empleado) {	 
		empleadoService.guardarEmpleado(empleado);		 
    	return "redirect:/empleado?success";
	}

    
	@GetMapping("/empleado/edit/{id}")
	public String editProducto(@PathVariable Long id, Model model) {
   		Empleado empleado = empleadoService.buscarEmpleadooById(id);	       
   		model.addAttribute("empleado", empleado);
        model.addAttribute("areaList", areaLaboralService.listarTodosAreaLaboral());
        model.addAttribute("cargoList", cargoEmpleadoService.listarTodosCargoEmpleado());	        
    	return "empleado/edit";
	}	
	
	@PostMapping("/empleado/{id}")
	public String updateEmpleado(@PathVariable Long id, Empleado empleado, Model model) {
	    Empleado existenteEmpleado = empleadoService.buscarEmpleadooById(id);
	    existenteEmpleado.setId(id);
	    existenteEmpleado.setNombres(empleado.getNombres());
	    existenteEmpleado.setApellidos(empleado.getApellidos());
	    existenteEmpleado.setArea(empleado.getArea());
	    existenteEmpleado.setCargo(empleado.getCargo());
	    existenteEmpleado.setNombreCiudad(empleado.getNombreCiudad());
	    existenteEmpleado.setDireccionEmpleado(empleado.getDireccionEmpleado());
	    existenteEmpleado.setTelefono(empleado.getTelefono());
	    empleadoService.actualizarEmpleado(existenteEmpleado);
	    return "redirect:/empleado";
	}
}

