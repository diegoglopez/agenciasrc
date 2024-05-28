package com.agencia.pf.models.rapipago;

import java.util.ArrayList;
import java.util.List;

public class RapipagoResponseConsulta {

    private String id_clave;
    private String nombre;
    private String apellido;
    private String cod_trx;
    private String codigo_respuesta;
    private String msg;
    private String dato_adicional;
    private List facturas;

    public RapipagoResponseConsulta(String id_clave, String nombre, String apellido, String cod_trx, String codigo_respuesta, String msg, String dato_adicional, List facturas) {
        this.id_clave = id_clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cod_trx = cod_trx;
        this.codigo_respuesta = codigo_respuesta;
        this.msg = msg;
        this.dato_adicional = dato_adicional;
        this.facturas = facturas;
    }

    public RapipagoResponseConsulta() {
        this.facturas = new ArrayList<RapipagoFactura>();
        this.setCodigo_respuesta("0");
        this.setMsg("Trx ok");
        this.setDato_adicional("Cobro Deuda");
    }

    public String getId_clave() {
        return id_clave;
    }

    public void setId_clave(String id_clave) {
        this.id_clave = id_clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCod_trx() {
        return cod_trx;
    }

    public void setCod_trx(String cod_trx) {
        this.cod_trx = cod_trx;
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

    public String getDato_adicional() {
        return dato_adicional;
    }

    public void setDato_adicional(String dato_adicional) {
        this.dato_adicional = dato_adicional;
    }

    public List getFacturas() {
        return facturas;
    }

    public void setFacturas(List facturas) {
        this.facturas = facturas;
    }
}
