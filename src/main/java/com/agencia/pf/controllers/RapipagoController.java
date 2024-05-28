package com.agencia.pf.controllers;

import com.agencia.pf.models.rapipago.RapipagoConsulta;
import com.agencia.pf.models.rapipago.RapipagoEnvioPagoReversa;
import com.agencia.pf.models.rapipago.RapipagoResponseConsulta;
import com.agencia.pf.models.rapipago.RapipagoResponseEnvioPagoReversa;
import com.agencia.pf.services.RapipagoService;
import com.agencia.pf.utiles.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/rpp")
public class RapipagoController {
    @Autowired
    RapipagoService rapipagoService;
    private Logger logger = Logger.getLogger(RapipagoController.class.getName());


    @PostMapping("/consulta")
    public ResponseEntity<RapipagoResponseConsulta> getDatosRPP(@RequestBody RapipagoConsulta rppc) {
        logger.info("Consulta Rapipago:" + rppc);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        RapipagoResponseConsulta response = rapipagoService.consultaRapipago(rppc);
        logger.info("Response:" + response);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/pago")
    public ResponseEntity<RapipagoResponseEnvioPagoReversa> envioPago(@RequestBody RapipagoEnvioPagoReversa rppe) {
        logger.info("Envio Pago:" + rppe);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        RapipagoResponseEnvioPagoReversa response = rapipagoService.envio(rppe, Constants.ENVIO_PAGO);
        logger.info("Response:" + response);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping("/reversa")
    public ResponseEntity<RapipagoResponseEnvioPagoReversa> reversaPago(@RequestBody RapipagoEnvioPagoReversa rppe) {
        logger.info("Envio Pago:" + rppe);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        RapipagoResponseEnvioPagoReversa response = rapipagoService.envio(rppe, Constants.REVERSA);
        logger.info("Response:" + response);
        return ResponseEntity.ok().headers(headers).body(response);
    }

}
