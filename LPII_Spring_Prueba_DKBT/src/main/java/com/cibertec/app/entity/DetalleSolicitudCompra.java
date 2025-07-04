package com.cibertec.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "detalle_solicitud_compra")
@IdClass(DetalleSolicitudCompraId.class)
public class DetalleSolicitudCompra {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_solicitud_compra", nullable = false)
    private SolicitudCompra solicitudCompra;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;
}