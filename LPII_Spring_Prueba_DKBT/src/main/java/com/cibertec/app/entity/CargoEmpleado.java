package com.cibertec.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cargo_empleado")
public class CargoEmpleado {

	@Id
	@Column(name = "id_cargo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descripcion")
	private String descripcion;

	public CargoEmpleado() {
	}

	public CargoEmpleado(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	

}
