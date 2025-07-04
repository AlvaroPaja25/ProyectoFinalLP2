package com.cibertec.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProveedorPrecioDTO {
    private Long idProveedor;
    private String nombreProveedor;
    private BigDecimal precio;
}
