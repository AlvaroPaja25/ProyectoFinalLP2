package com.cibertec.app.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_solicitud")
public class TipoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_solicitud")
    private Long id;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    public TipoSolicitud() {}

    public TipoSolicitud(String descripcion) {
        this.descripcion = descripcion;
    }
}