package com.cibertec.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
	
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "solicitud_compra")
public class SolicitudCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_compra")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "fecha_emision", nullable = true)
    private Date fechaEmision;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_orden")
    private EstadoOrden estadoOrden = EstadoOrden.Emitida;

    @Column(name = "observaciones", length = 200)
    private String observaciones;

    @Column(name = "total_gasto", precision = 10, scale = 2)
    private BigDecimal totalGasto;

    public enum EstadoOrden {
        Emitida, Aprobado, Rechazado
    }
}