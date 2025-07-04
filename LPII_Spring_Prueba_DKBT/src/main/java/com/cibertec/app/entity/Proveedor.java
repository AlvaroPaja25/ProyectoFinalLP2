package com.cibertec.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "proveedor")
public class Proveedor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProveedor estado = EstadoProveedor.Activo;

    public enum EstadoProveedor {
        Activo, Inactivo
    }
}