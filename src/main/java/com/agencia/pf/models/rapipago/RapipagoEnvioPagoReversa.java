package com.agencia.pf.models.rapipago;

import java.util.Date;

public class RapipagoEnvioPagoReversa {

    private String id_numero;
    private String cod_trx;
    private String canal;
    private String importe;
    private String barra;
    private String fecha_hora_operacion;

    public RapipagoEnvioPagoReversa() {
    }

    public RapipagoEnvioPagoReversa(String id_numero, String cod_trx, String canal, String importe, String barra, String fecha_hora_operacion) {
        this.id_numero = id_numero;
        this.cod_trx = cod_trx;
        this.canal = canal;
        this.importe = importe;
        this.barra = barra;
        this.fecha_hora_operacion = fecha_hora_operacion;
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

    public RapipagoLogEnvioPagoReversa toLog(String tipo){
        RapipagoLogEnvioPagoReversa log = new RapipagoLogEnvioPagoReversa();
        log.setId_numero(this.getId_numero());
        log.setCod_trx(this.getCod_trx());
        log.setCanal(this.getCanal());
        log.setImporte(this.getImporte());
        log.setBarra(this.getBarra());
        log.setFecha_hora_operacion(this.getFecha_hora_operacion());
        log.setTipo(tipo);
        log.setFechacarga(new Date());

        return log;

    }

    @Override
    public String toString() {
        return "RapipagoEnvioPago{" +
                "id_numero='" + id_numero + '\'' +
                ", cod_trx='" + cod_trx + '\'' +
                ", canal='" + canal + '\'' +
                ", importe='" + importe + '\'' +
                ", barra='" + barra + '\'' +
                ", fecha_hora_operacion='" + fecha_hora_operacion + '\'' +
                '}';
    }
}
