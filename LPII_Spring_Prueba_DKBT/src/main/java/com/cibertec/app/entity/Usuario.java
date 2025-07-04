package com.cibertec.app.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usuario")
	private String username;
	
	@Column(name = "clave")
	private String clave;
	
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name= "id_rol")
	private Rol rol;
	
	
	public Usuario() {
	}


	public Usuario(String username, String clave) {
		super();
		this.username = username;
		this.clave = clave;
	}


	public Usuario(String username, String clave, Empleado empleado, Rol rol) {
		super();
		this.username = username;
		this.clave = clave;
		this.empleado = empleado;
		this.rol = rol;
	}


	public Usuario(Long id, String username, String clave, Empleado empleado, Rol rol) {
		super();
		this.id = id;
		this.username = username;
		this.clave = clave;
		this.empleado = empleado;
		this.rol = rol;
	}


}