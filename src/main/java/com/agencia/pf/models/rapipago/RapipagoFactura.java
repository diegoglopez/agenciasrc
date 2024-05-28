package com.agencia.pf.models.rapipago;

public class RapipagoFactura {
    private String id_numero;
    private String fecha_vencimiento;
    private String fecha_emision;
    private String importe;
    private String barra;
    private String texto1;

    public RapipagoFactura() {
    }

    public RapipagoFactura(String id_numero, String fecha_vencimiento, String fecha_emision, String importe, String barra, String texto1) {
        this.id_numero = id_numero;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_emision = fecha_emision;
        this.importe = importe;
        this.barra = barra;
        this.texto1 = texto1;
    }

    public String getId_numero() {
        return id_numero;
    }

    public void setId_numero(String id_numero) {
        String idfactura = String.format("%15s",  id_numero).replace(" ","0");
        this.id_numero = idfactura;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
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

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }
}
