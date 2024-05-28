package com.agencia.pf.models.rapipago;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rapipagologconsulta")
public class RapipagoLogConsulta {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_clave;
    private String cod_trx;
    private String canal;
    private String fecha_hora_operacion;
    private Date fechacarga;

    public RapipagoLogConsulta(Long id, String id_clave, String cod_trx, String canal, String fecha_hora_operacion) {
        this.id = id;
        this.id_clave = id_clave;
        this.cod_trx = cod_trx;
        this.canal = canal;
        this.fecha_hora_operacion = fecha_hora_operacion;
    }

    public RapipagoLogConsulta() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_clave() {
        return id_clave;
    }

    public void setId_clave(String id_clave) {
        this.id_clave = id_clave;
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

    @Override
    public String toString() {
        return "RapipagoLogConsulta{" +
                "id=" + id +
                ", id_clave='" + id_clave + '\'' +
                ", cod_trx='" + cod_trx + '\'' +
                ", canal='" + canal + '\'' +
                ", fecha_hora_operacion='" + fecha_hora_operacion + '\'' +
                ", fechacarga=" + fechacarga +
                '}';
    }
}
