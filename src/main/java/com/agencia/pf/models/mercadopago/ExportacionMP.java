package com.agencia.pf.models.mercadopago;


import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "exportacionmp")
public class ExportacionMP {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Date fechacarga;
    @OneToOne
    @JoinColumn(name = "idusuariocarga", referencedColumnName = "id")
    private Usuario usuario;
    private Boolean activo;

    public ExportacionMP() {
        this.setFechacarga(new Date());
        this.setActivo(true);
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public ExportacionMPDTO toDto(){
        ExportacionMPDTO exportacionMPDTO = new ExportacionMPDTO();
        UsuarioDTO usuarioDTO=(this.getUsuario()!=null)?this.getUsuario().toDto():null;
        exportacionMPDTO.setDescripcion(this.getDescripcion());
        exportacionMPDTO.setFechacarga(this.getFechacarga());
        exportacionMPDTO.setUsuario(usuarioDTO);
        exportacionMPDTO.setId(this.getId());
        exportacionMPDTO.setActivo(this.getActivo());
        exportacionMPDTO.setDescripcion(this.getDescripcion());

        return exportacionMPDTO;
    }
}
