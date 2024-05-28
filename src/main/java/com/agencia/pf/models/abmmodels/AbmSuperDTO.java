package com.agencia.pf.models.abmmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbmSuperDTO {
    private Long id;
    private String descripcion;
    private String usuario;
    private Date fecha;
    private boolean estado;
}
