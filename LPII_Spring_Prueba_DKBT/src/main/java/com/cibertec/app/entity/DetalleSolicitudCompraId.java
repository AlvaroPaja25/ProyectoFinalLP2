package com.cibertec.app.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class DetalleSolicitudCompraId implements Serializable {
    private Long solicitudCompra;
    private Long solicitud;
}
