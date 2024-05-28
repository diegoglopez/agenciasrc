package com.agencia.pf.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "operacion")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "idtipooperacion", referencedColumnName = "id")
    private TipoOperacion tipoOperacion;
    private String observacion;
    private Date comienzo;
    private Date fin;
    private Integer registrosafectados;


}
