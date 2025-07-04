package com.cibertec.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_solicitud")
public class DetalleSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_solicitud")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;
    
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    
    @Column(name = "justificacion", length = 200)
    private String justificacion;

    @Column(name = "cantidad")
    private Integer cantidad;

    public DetalleSolicitud() {}
}
