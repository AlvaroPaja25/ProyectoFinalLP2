package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.app.entity.Usuario;
import com.cibertec.app.entity.Empleado;
import com.cibertec.app.service.RolService;
import com.cibertec.app.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@Autowired
	private RolService rolService;

	@GetMapping("/")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model){
	    Usuario userDto = new Usuario();
	    userDto.setEmpleado(new Empleado()); 
	    model.addAttribute("usuario", userDto);
	    return "register";
	}
	
	

	  @PostMapping("/register/save")
	    public String registration(@ModelAttribute("usuario") Usuario usuario, BindingResult result,Model model){ //Model attribute is used to extract model object which is a form data.


	        Usuario existingUser = usuarioService.buscarByUsuario(usuario.getUsername()); //checking if entered email already exists or not.
	        
             if(usuario.getUsername()==null || usuario.getUsername().isEmpty()){
  	            result.rejectValue("username",null,"Ingresar username");
  	        }
             
             if(usuario.getClave()==null || usuario.getClave().isEmpty()){
  	            result.rejectValue("clave",null,"Ingresar clave");
  	        }
             
             
	        if(existingUser!=null && existingUser.getUsername()!=null && !existingUser.getUsername().isEmpty()){
	            result.rejectValue("username",null,"Ya existe una cuenta con este usuario");
	        }

	        if(result.hasErrors()){
	            model.addAttribute("usuario",usuario);
	            return "/register"; // if any form has errors it will be redirected to register page only.
	        }

	        
	        usuario.setRol(rolService.buscarById(2));//
	        usuarioService.guardarUsuario(usuario);
	        return "redirect:/register?success"; // @Valid from jakarta.validation will enable the validation fields of dto objectsto be enabled.

	    }
	
	@PostMapping("/login")
	public String iniciarSesion(Model model,@ModelAttribute("usuario") Usuario usuario) {//,BindingResult result) {
	
		boolean band=usuarioService.login(usuario);
		System.out.println("bandd--> "+band);
		
		if(band==true) {
			   model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
			   model.addAttribute("rolList", rolService.listarTodosRol());
			
			   return   "usuario/index";
			
			
		}else {
			return "error_login";
		}
		
		
	}
	
	 @GetMapping("/usuario/new")
	    public String createUsuarioForm(Model model){
	    	Usuario usuario = new Usuario();
	       
	        model.addAttribute("usuario", usuario);
	        model.addAttribute("rolList", rolService.listarTodosRol());
	       
	        return "register";
	     
	    }
	
	 
	 @PostMapping("/usuario")
	    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
		 usuarioService.guardarUsuario(usuario);
	        return "redirect:/usuario";
	    }
	 

	    @GetMapping("/usuario")
	    public String listUsuarios(Model model) {
	        model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
	        model.addAttribute("rolList", rolService.listarTodosRol());
	       
	        
	        return "usuario/index";
	    }
	    
	    @GetMapping("/usuario/{id}") 
	    public String deleteProducto(@PathVariable Long id) {
	    	usuarioService.eliminarUsuario(id);
	    	return "redirect:/usuario";
    }	
	
}