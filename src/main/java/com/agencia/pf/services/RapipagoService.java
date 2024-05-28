package com.agencia.pf.services;

import com.agencia.pf.models.rapipago.*;
import com.agencia.pf.repositories.RapipagoConsolidadoRepository;
import com.agencia.pf.repositories.RapipagoLogConsultaRepository;
import com.agencia.pf.repositories.RapipagoLogEnvioPagoReversaRepository;
import com.agencia.pf.utiles.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.agencia.pf.utiles.Utiles.ver;

@Service
public class RapipagoService {
    @Autowired
    RapipagoConsolidadoRepository rapipagoConsolidadoRepository;
    @Autowired
    RapipagoLogConsultaRepository rapipagoLogConsultaRepository;
    @Autowired
    RapipagoLogEnvioPagoReversaRepository rapipagoLogEnvioPagoReversaRepository;


    private Logger logger = Logger.getLogger(RapipagoService.class.getName());

    public RapipagoResponseConsulta consultaRapipago(RapipagoConsulta rppc) {
        RapipagoResponseConsulta response = new RapipagoResponseConsulta();
        List<Rapipagoconsolidado> listdeudores=null;

        try {
            RapipagoLogConsulta rpcLog = rppc.toLog();
            rapipagoLogConsultaRepository.save(rpcLog);
        }catch (Exception e){
            // 1 Error en BD
            logger.log(Level.WARNING, "ERROR : NO SE PUDO RECUPERAR EL DATO EN LA BDD, idClave: " + rppc.getId_clave() +  e.getMessage());
            response.setCodigo_respuesta(Constants.ERROR_DB_N);
            response.setMsg(Constants.ERROR_DB_MSG);
            return response;
        }

        if(this.checkErrorRequest(rppc)){
            // 9 Parámetros incorrectos o faltantes
            response.setCodigo_respuesta(Constants.PARAMETROS_INCORRECTOS_FALTANTES_N);
            response.setMsg(Constants.PARAMETROS_INCORRECTOS_FALTANTES_MSG);
            return response;
        }


        try {
            Long ldoc = Long.parseLong(rppc.getId_clave());
            listdeudores =  rapipagoConsolidadoRepository.findByDocumento(ldoc.toString());
        }catch (Exception e){
            // 1 Error en BD
            logger.log(Level.WARNING, "ERROR : NO SE PUDO RECUPERAR EL DATO EN LA BDD, idClave: " + rppc.getId_clave() +  e.getMessage());
            response.setCodigo_respuesta(Constants.ERROR_DB_N);
            response.setMsg(Constants.ERROR_DB_MSG);
            return response;
        }

        if((listdeudores.size()==0 || listdeudores==null)){
            // 7 Cliente no existe
            response.setCodigo_respuesta(Constants.CLIENTE_NO_EXISTE_N);
            response.setMsg(Constants.CLIENTE_NO_EXISTE_MSG);
            return response;
        }

        try {
            // Date fechaOperacion = null;
            // fechaOperacion = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(rppc.getFecha_hora_operacion());

            LocalDateTime fechaEmision = LocalDateTime.now();
            String sfechaEmision = fechaEmision.toString().substring(0,10);
            LocalDateTime fechaVencimiento = fechaEmision.plusMonths(1l);
            String sfechaVencimiento = fechaVencimiento.toString().substring(0,10);


            List facturas = new ArrayList<RapipagoFactura>();
            for(Rapipagoconsolidado rp : listdeudores){
                ver(rp);
                response.setId_clave(rppc.getId_clave());
                response.setNombre(rp.getNombre());
                response.setApellido("");
                response.setCod_trx(rppc.getCod_trx());
                RapipagoFactura factura = new RapipagoFactura();
                // String saldo = this.formatImporte(Math.round(rp.getSaldo() *100.0)/100.0);
                String saldo = this.formatImporte(0.0);
                factura.setImporte(saldo);
                factura.setId_numero(rp.getCodigofactura());
                factura.setFecha_emision(sfechaEmision);
                factura.setFecha_vencimiento(sfechaVencimiento);

                String barra = crearBarra(response.getId_clave(), factura.getId_numero(), factura.getImporte(), factura.getFecha_vencimiento());
                factura.setBarra(barra);
                factura.setTexto1(response.getNombre() + ", Cobro Deuda Via Contacto");
                facturas.add(factura);
            }
            response.setFacturas(facturas);
        } catch (Exception e) {
            logger.log(Level.WARNING,"ERROR : No se pudo enviar la consulta, Id_clave :" + rppc.getId_clave());
            e.printStackTrace();
        }

        return response;
    }

    private Boolean checkErrorRequest (RapipagoConsulta rppc){
        Boolean error = false;
        if(rppc.getId_clave()==null)
            error=true;
        if(!rppc.getCanal().equals(Constants.CANAL_RAPIPAGO))
            error=true;

        if(rppc.getCod_trx()==null)
            error=true;
        else if(rppc.getCod_trx().equals(StringUtils.EMPTY))
            error=true;

        if(rppc.getFecha_hora_operacion()==null )
            error=true;
        else if (rppc.getFecha_hora_operacion().length()!=19)
            error=true;

        return error;

    }

    public RapipagoResponseEnvioPagoReversa envio(RapipagoEnvioPagoReversa rppe, String tipo){

        RapipagoResponseEnvioPagoReversa response = new RapipagoResponseEnvioPagoReversa();
        List<Rapipagoconsolidado> listdeudores = null;

        try {
            RapipagoLogEnvioPagoReversa rpcLog = rppe.toLog(tipo);
            rapipagoLogEnvioPagoReversaRepository.save(rpcLog);
        }catch (Exception e){
            // 1 Error en BD
            logger.log(Level.WARNING, "ERROR : NO SE PUDO RECUPERAR EL DATO EN LA BDD, Factura con Id_numero: " + rppe.getId_numero() +  e.getMessage());
            response.setCodigo_respuesta(Constants.ERROR_DB_N);
            response.setMsg(Constants.ERROR_DB_MSG);
            return response;
        }

        if(this.checkErrorRequest(rppe)){
            // 9 Parámetros incorrectos o faltantes
            response.setCodigo_respuesta(Constants.PARAMETROS_INCORRECTOS_FALTANTES_N);
            response.setMsg(Constants.PARAMETROS_INCORRECTOS_FALTANTES_MSG);
            return response;
        }

        try {
            listdeudores =  rapipagoConsolidadoRepository.findByCodigofactura(rppe.getId_numero());
        }catch (Exception e){
            // 1 Error en BD
            logger.log(Level.WARNING, "ERROR : NO SE PUDO RECUPERAR EL DATO EN LA BDD, Factura con Id_numero: " + rppe.getId_numero() +  e.getMessage());
            response.setCodigo_respuesta(Constants.ERROR_DB_N);
            response.setMsg(Constants.ERROR_DB_MSG);
            return response;
        }

        if((listdeudores.size()==0 || listdeudores==null)){
            if(tipo.equals(Constants.REVERSA)){
                // 4 No existe el Pago (cuando se quiere reversaruna transacción queno existe)
                response.setCodigo_respuesta(Constants.NO_EXISTE_EL_PAGO_N);
                response.setMsg(Constants.NO_EXISTE_EL_PAGO_MSG);
                return response;
            } else {
                // 7 Cliente no existe
                response.setCodigo_respuesta(Constants.CLIENTE_NO_EXISTE_N);
                response.setMsg(Constants.CLIENTE_NO_EXISTE_MSG);
                return response;
            }
        }

        try {

            // listdeudores =  rapipagoConsolidadoRepository.findByCodigofactura(rppe.getId_numero());

            for(Rapipagoconsolidado res : listdeudores){
                Double importe = Double.parseDouble(rppe.getImporte());
                if(tipo.equals(Constants.REVERSA)){
                    if(res.getFechareversa()!=null){
                        // 3 Transacción ya reversada
                        response.setCodigo_respuesta(Constants.TRANSACCION_YA_REVERSADA_N);
                        response.setMsg(Constants.TRANSACCION_YA_REVERSADA_MSG);
                        return response;
                    }
                    res.setMontoreversa(importe);
                    res.setFechareversa(new Date());
                } else {
                    if(res.getFechadepago()!=null){
                        // 14 Pago registrado con anteriodad
                        response.setCodigo_respuesta(Constants.PAGO_YA_REGISTRADO_CON_ATERIORIDAD_N);
                        response.setMsg(Constants.PAGO_YA_REGISTRADO_CON_ATERIORIDAD_MSG);
                        return response;
                    }
                    res.setMontopagado(importe);
                    res.setFechadepago(new Date());
                }

                response.setId_numero(rppe.getId_numero());
                response.setCod_trx(rppe.getCod_trx());
                response.setBarra(rppe.getBarra());
                response.setFecha_hora_operacion(rppe.getFecha_hora_operacion());

                response.setCodigo_respuesta(Constants.TRANSACCION_COMPLETA_N);
                response.setMsg(Constants.TRANSACCION_COMPLETA_MSG);

                res.setCod_trx(rppe.getCod_trx());
                logger.info("Modificando Registro con Id: " + res.getId()  + " ...");
                rapipagoConsolidadoRepository.save(res);
                logger.info("Modificado");
            }
        }catch (Exception e){
            logger.log(Level.WARNING,"ERROR : No se pudo modificar el registro en la base, Id_numero :" + rppe.getId_numero());
            e.printStackTrace();
        }
        return response;

    }

    private Boolean checkErrorRequest (RapipagoEnvioPagoReversa rppe){
        Boolean error = false;
        if(rppe.getId_numero()==null)
            error=true;
        if(!rppe.getCanal().equals(Constants.CANAL_RAPIPAGO))
            error=true;
        if(rppe.getCod_trx()==null)
            error=true;
        else if(rppe.getCod_trx().equals(StringUtils.EMPTY))
            error=true;
        if(rppe.getFecha_hora_operacion()==null )
            error=true;
        else if (rppe.getFecha_hora_operacion().length()!=19)
            error=true;
        if(rppe.getImporte()==null)
            error=true;
        else if(rppe.getImporte().equals(StringUtils.EMPTY))
            error=true;
        if(rppe.getBarra()==null)
            error=true;
        else if(rppe.getBarra().equals(StringUtils.EMPTY))
            error=true;

        return error;

    }


    private String formatImporte(Double importe){
        String simporte = String.format("%11s", importe.toString()).replace(" ","0");
        return simporte;
    }

    private String crearBarra(String id_clave, String factura, String importe, String fechaEmision){
        String simporte = String.format("%11s", importe.replace(".","")).replace(" ","0");
        String clave = String.format("%19s", id_clave).replace(" ","0");
        return clave + factura + simporte + fechaEmision.replaceAll("-","");
    }

}
