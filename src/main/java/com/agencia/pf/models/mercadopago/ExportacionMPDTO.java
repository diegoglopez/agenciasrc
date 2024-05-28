package com.agencia.pf.models.mercadopago;

import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.UsuarioDTO;

import java.util.Date;

public class ExportacionMPDTO {

    private Long id;
    private String descripcion;
    private Date fechacarga;
    private UsuarioDTO usuario;
    private Boolean activo;

    public ExportacionMPDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechacarga() {
        return fechacarga;
    }

    public void setFechacarga(Date fechacarga) {
        this.fechacarga = fechacarga;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
