package com.cibertec.app.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleSolicitudDTO {
    private Long idSolicitudOriginal;
    private Long idProducto;
    private String nombre;
    private Integer totalCantidad;
    private List<ProveedorPrecioDTO> proveedores;
}

