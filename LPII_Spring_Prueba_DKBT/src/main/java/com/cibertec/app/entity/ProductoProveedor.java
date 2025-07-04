package com.cibertec.app.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "producto_proveedor")
public class ProductoProveedor{


	@EmbeddedId
    private ProductoProveedorId id;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoProductoProveedor estado = EstadoProductoProveedor.Habilitado;

    public enum EstadoProductoProveedor {
        Habilitado, Deshabilitado
    }
}
