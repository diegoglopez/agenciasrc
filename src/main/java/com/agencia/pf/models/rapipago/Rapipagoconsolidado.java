package com.agencia.pf.models.rapipago;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rapipagoconsolidado")
public class Rapipagoconsolidado {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numerocliente;
    private String documento;
    private String motorbusqueda;
    private String nombre;
    private Double saldo;
    private Double saldoactualizado;
    private String codigofactura;
    private String numerodeproducto;
    private Date fechadepago;
    private Double montopagado;
    private String cod_trx;
    private Double montoreversa;
    private Date fechareversa;



    public Rapipagoconsolidado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerocliente() {
        return numerocliente;
    }

    public void setNumerocliente(String numerocliente) {
        this.numerocliente = numerocliente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getMotorbusqueda() {
        return motorbusqueda;
    }

    public void setMotorbusqueda(String motorbusqueda) {
        this.motorbusqueda = motorbusqueda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldoactualizado() {
        return saldoactualizado;
    }

    public void setSaldoactualizado(Double saldoactualizado) {
        this.saldoactualizado = saldoactualizado;
    }

    public String getCodigofactura() {
        return codigofactura;
    }

    public void setCodigofactura(String codigofactura) {
        this.codigofactura = codigofactura;
    }

    public String getNumerodeproducto() {
        return numerodeproducto;
    }

    public void setNumerodeproducto(String numerodeproducto) {
        this.numerodeproducto = numerodeproducto;
    }

    public Date getFechadepago() {
        return fechadepago;
    }

    public void setFechadepago(Date fechadepago) {
        this.fechadepago = fechadepago;
    }

    public Double getMontopagado() {
        return montopagado;
    }

    public void setMontopagado(Double montopagado) {
        this.montopagado = montopagado;
    }

    public String getCod_trx() {
        return cod_trx;
    }

    public void setCod_trx(String cod_trx) {
        this.cod_trx = cod_trx;
    }

    public Double getMontoreversa() {
        return montoreversa;
    }

    public void setMontoreversa(Double montoreversa) {
        this.montoreversa = montoreversa;
    }

    public Date getFechareversa() {
        return fechareversa;
    }

    public void setFechareversa(Date fechareversa) {
        this.fechareversa = fechareversa;
    }

    @Override
    public String toString() {
        return "Rapipagoconsolidado{" +
                "id=" + id +
                ", numerocliente='" + numerocliente + '\'' +
                ", documento='" + documento + '\'' +
                ", motorbusqueda='" + motorbusqueda + '\'' +
                ", nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", saldoactualizado=" + saldoactualizado +
                ", codigofactura='" + codigofactura + '\'' +
                ", numerodeproducto='" + numerodeproducto + '\'' +
                ", fechadepago=" + fechadepago +
                ", montopagado=" + montopagado +
                ", cod_trx='" + cod_trx + '\'' +
                ", montoreversa=" + montoreversa +
                ", fechareversa=" + fechareversa +
                '}';
    }
}
