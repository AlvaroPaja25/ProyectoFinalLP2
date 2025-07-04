package com.cibertec.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "empleado")
public class Empleado {

    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private AreaLaboral area;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;

    @Column(name = "direccion_empleado")
    private String direccionEmpleado;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private CargoEmpleado cargo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEmpleado estado = EstadoEmpleado.Activo;

    public enum EstadoEmpleado {
        Activo, Inactivo
    }

    public Empleado() {
    }
    
    public Empleado(String nombres, String apellidos, AreaLaboral area, String nombreCiudad, String direccionEmpleado, String telefono, CargoEmpleado cargo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.area = area;
        this.nombreCiudad = nombreCiudad;
        this.direccionEmpleado = direccionEmpleado;
        this.telefono = telefono;
        this.cargo = cargo;
    }
    
	public Empleado(String nombres, String apellidos) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
	}
}
