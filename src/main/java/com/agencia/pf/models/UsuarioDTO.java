package com.agencia.pf.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String usuario;
    private String mail;
    private String pass;
    private Integer idpais;
    private boolean activo;



}

