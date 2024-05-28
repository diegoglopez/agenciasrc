package com.agencia.pf.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
//    private Rol rol;
    private Integer idrol;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "pass", nullable = false)
    private String pass;
    @Column(name = "fechapass")
    private Date fechaPass;
    private String mail;
    @Column(name = "qintentospass")
    private Integer qIntentosPass;
    private Boolean bloqueado;
    private Boolean loggeado;
    @Column(name = "fechaultimologgin")
    private Date fechaUltimoLoggin;
    @Column(name = "fechaalta")
    private Date fechaAlta;
    private Integer idpais;
    private boolean activo;

    public UsuarioDTO toDto(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario(this.getUsuario());
        usuarioDTO.setId(this.getId());
        usuarioDTO.setActivo(this.isActivo());
        usuarioDTO.setMail(this.getMail());
        usuarioDTO.setNombre(this.getNombre());
        usuarioDTO.setIdpais(this.getIdpais());

        return usuarioDTO;
    }


}

