package com.agencia.pf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tipodocumento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fecha", nullable = false)
    private Date fecha;

}
