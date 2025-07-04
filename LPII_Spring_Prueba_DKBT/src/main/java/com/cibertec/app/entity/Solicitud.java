package com.cibertec.app.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_solicitud", nullable = false)
    private TipoSolicitud tipoSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private AreaLaboral area;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud")
    private EstadoSolicitud estadoSolicitud = EstadoSolicitud.Pendiente;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleSolicitud> detalles;

    
    public enum EstadoSolicitud {
        Pendiente,
        Recibida,
        Rechazado,
        Aprobado
    }
}
