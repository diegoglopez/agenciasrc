package com.agencia.pf.models.rapipago;

import java.util.Date;

public class RapipagoConsulta {
    private String id_clave;
    private String cod_trx;
    private String canal;
    private String fecha_hora_operacion;

    public RapipagoConsulta(String id_clave, String cod_trx, String canal, String fecha_hora_operacion) {
        this.id_clave = id_clave;
        this.cod_trx = cod_trx;
        this.canal = canal;
        this.fecha_hora_operacion = fecha_hora_operacion;
    }

    public RapipagoConsulta() {
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

    public RapipagoLogConsulta toLog(){
        RapipagoLogConsulta log = new RapipagoLogConsulta();
        log.setCanal(this.getCanal());
        log.setCod_trx(this.getCod_trx());
        log.setFecha_hora_operacion(this.getFecha_hora_operacion());
        log.setId_clave(this.getId_clave());
        log.setFechacarga(new Date());

        return log;
    }

    @Override
    public String toString() {
        return "RapipagoConsulta{" +
                "id_clave='" + id_clave + '\'' +
                ", cod_trx='" + cod_trx + '\'' +
                ", canal='" + canal + '\'' +
                ", fecha_hora_operacion='" + fecha_hora_operacion + '\'' +
                '}';
    }
}
