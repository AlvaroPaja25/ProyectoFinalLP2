
package com.cibertec.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SolicitudCompraFormDTO {
    private Long empleadoId;
    private String observaciones;
    private BigDecimal totalGasto;
    private List<DetalleOrdenCompraDTO> detallesOrdenCompra = new ArrayList<>();
    private List<Long> idSolicitudesOriginales = new ArrayList<>();
}

