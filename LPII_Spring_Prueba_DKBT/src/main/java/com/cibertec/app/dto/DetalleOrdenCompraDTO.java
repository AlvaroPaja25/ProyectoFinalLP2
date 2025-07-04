package com.cibertec.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DetalleOrdenCompraDTO {
    private Long idProducto;
    private Long idProveedor;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
