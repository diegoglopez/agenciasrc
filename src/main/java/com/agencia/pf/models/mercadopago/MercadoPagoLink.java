package com.agencia.pf.models.mercadopago;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.util.Date;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.REFRESH;


@Entity
@Table(name = "mercadopagolink")
public class MercadoPagoLink {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    @Column(name = "idcompania")
    private Integer compania;
    @Column(name = "idtipopago")
    private Integer idTipoPago;
    private String mail;
    private String cartera;
    private String entidad;
    private String codigo;
    private String descripcion;
    // @ManyToOne(cascade = {CascadeType.ALL})

    @ManyToOne(cascade = {MERGE, REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "idexportacionmp", referencedColumnName = "id")
    ExportacionMP exportacionMP;
    private BigDecimal importe;
    @Column(name = "linkmp")
    private String linkMP;

    public MercadoPagoLink() {
    }

    public MercadoPagoLink(Long id, String nombre, String documento, String mail, String cartera, String entidad, String codigo, String descripcion, ExportacionMP exportacionMP, BigDecimal importe, String linkMP) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.mail = mail;
        this.cartera = cartera;
        this.entidad = entidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.exportacionMP = exportacionMP;
        this.importe = importe;
        this.linkMP = linkMP;
    }

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public Integer getCompania() {
        return compania;
    }

    public void setCompania(Integer compania) {
        this.compania = compania;
    }

    public Long getId() {
        return id;
    }

    public String getEntidad() {
        return entidad;
    }

    public ExportacionMP getExportacionMP() {
        return exportacionMP;
    }

    public void setExportacionMP(ExportacionMP exportacionMP) {
        this.exportacionMP = exportacionMP;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCartera() {
        return cartera;
    }

    public void setCartera(String cartera) {
        this.cartera = cartera;
    }

    public String getsetEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getLinkMP() {
        return linkMP;
    }

    public void setLinkMP(String linkMP) {
        this.linkMP = linkMP;
    }

    @Override
    public String toString() {
        return "MercadoPagoLink{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", mail='" + mail + '\'' +
                ", cartera='" + cartera + '\'' +
                ", entidad='" + entidad + '\'' +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", importe=" + importe +
                ", linkMP='" + linkMP + '\'' +
                '}';
    }
}
