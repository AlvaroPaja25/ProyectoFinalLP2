package com.cibertec.app.entity;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_comercial", nullable = false, length = 100)
    private String nombreComercial;

    @Column(name = "razon_social", nullable = false, length = 150)
    private String razonSocial;

    @Column(name = "rubro", nullable = false, length = 100)
    private String rubro;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "ruc", nullable = false, length = 11, unique = true)
    private String ruc;

    @Column(name = "codigo_facturacion", length = 20)
    private String codigoFacturacion;

    @Column(name = "logo", length = 255)
    private String logo;
}
