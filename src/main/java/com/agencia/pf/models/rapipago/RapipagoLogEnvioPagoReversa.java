package com.agencia.pf.models.rapipago;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rapipagologenviopagoreversa")
public class RapipagoLogEnvioPagoReversa {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_numero;
    private String cod_trx;
    private String canal;
    private String importe;
    private String barra;
    private String fecha_hora_operacion;
    private String tipo;
    private Date fechacarga;

    public RapipagoLogEnvioPagoReversa() {
    }

    public RapipagoLogEnvioPagoReversa(Long id, String id_numero, String cod_trx, String canal, String importe, String barra, String fecha_hora_operacion, Date fechacarga) {
        this.id = id;
        this.id_numero = id_numero;
        this.cod_trx = cod_trx;
        this.canal = canal;
        this.importe = importe;
        this.barra = barra;
        this.fecha_hora_operacion = fecha_hora_operacion;
        this.fechacarga = fechacarga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_numero() {
        return id_numero;
    }

    public void setId_numero(String id_numero) {
        this.id_numero = id_numero;
    }

    public String getCod_trx() {
        return cod_trx;
    }

    public void setCod_trx(String cod_trx) {
        this.cod_trx = cod_trx;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getFecha_hora_operacion() {
        return fecha_hora_operacion;
    }

    public void setFecha_hora_operacion(String fecha_hora_operacion) {
        this.fecha_hora_operacion = fecha_hora_operacion;
    }

    public Date getFechacarga() {
        return fechacarga;
    }

    public void setFechacarga(Date fechacarga) {
        this.fechacarga = fechacarga;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "RapipagoLogEnvioPagoReversa{" +
                "id=" + id +
                ", id_numero='" + id_numero + '\'' +
                ", cod_trx='" + cod_trx + '\'' +
                ", canal='" + canal + '\'' +
                ", importe='" + importe + '\'' +
                ", barra='" + barra + '\'' +
                ", fecha_hora_operacion='" + fecha_hora_operacion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechacarga=" + fechacarga +
                '}';
    }
}
