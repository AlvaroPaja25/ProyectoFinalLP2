package com.cibertec.app.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class DetalleOrdenCompraId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6166129230098987687L;
	private Long ordenCompra;
    private Long producto;
}
