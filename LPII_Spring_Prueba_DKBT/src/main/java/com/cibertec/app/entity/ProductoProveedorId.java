package com.cibertec.app.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class ProductoProveedorId {

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    
    public ProductoProveedorId(Proveedor proveedor, Producto producto) {
        this.proveedor = proveedor;
        this.producto = producto;
    }
}
