package com.agencia.pf.controllers;

import com.agencia.pf.models.mailsmasivos.MailPlantillaConfig;
import com.agencia.pf.models.mailsmasivos.MailPlantillaConfigDTO;
import com.agencia.pf.models.mercadopago.ExportacionMPDTO;
import com.agencia.pf.services.MailPlantillaConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/mailsConfig")
public class MailPlantillaConfigController {
    private Logger logger = Logger.getLogger(RapipagoController.class.getName());
    @Autowired
    MailPlantillaConfigService mailPlantillaConfigService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody MailPlantillaConfig plantilla) {
        logger.info("Ingreso a Create User " );
        HttpHeaders headers = new HttpHeaders();
        plantilla.setNombre("");
        mailPlantillaConfigService.save(plantilla);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/getAllByIdUsuario")
    public List<MailPlantillaConfigDTO> getAllByIdUsuario(@RequestParam("idusuario") Integer idusuario) {
        logger.info("Ingreso a Create User " );
        HttpHeaders headers = new HttpHeaders();
        List list = mailPlantillaConfigService.finAllByIdUsuario(idusuario);
        return list;
    }
}
