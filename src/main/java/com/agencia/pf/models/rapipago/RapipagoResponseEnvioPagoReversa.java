package com.agencia.pf.models.rapipago;

public class RapipagoResponseEnvioPagoReversa {

    private String id_numero;
    private String cod_trx;
    private String barra;
    private String fecha_hora_operacion;
    private String codigo_respuesta;
    private String msg;

    public RapipagoResponseEnvioPagoReversa() {
    }

    public RapipagoResponseEnvioPagoReversa(String id_numero, String cod_trx, String barra, String fecha_hora_operacion, String codigo_respuesta, String msg) {
        this.id_numero = id_numero;
        this.cod_trx = cod_trx;
        this.barra = barra;
        this.fecha_hora_operacion = fecha_hora_operacion;
        this.codigo_respuesta = codigo_respuesta;
        this.msg = msg;
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

    public String getCodigo_respuesta() {
        return codigo_respuesta;
    }

    public void setCodigo_respuesta(String codigo_respuesta) {
        this.codigo_respuesta = codigo_respuesta;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RapipagoResponseEnvioPago{" +
                "id_numero='" + id_numero + '\'' +
                ", cod_trx='" + cod_trx + '\'' +
                ", barra='" + barra + '\'' +
                ", fecha_hora_operacion='" + fecha_hora_operacion + '\'' +
                ", codigo_respuesta='" + codigo_respuesta + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
