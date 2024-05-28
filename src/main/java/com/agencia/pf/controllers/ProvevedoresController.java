package com.agencia.pf.controllers;

import com.agencia.pf.models.abmmodels.AbmSuperDTO;
import com.agencia.pf.models.ProveedorDTO;
import com.agencia.pf.models.abmmodels.Proveedor;
import com.agencia.pf.services.ProvedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.agencia.pf.utiles.Utiles.ver;
@CrossOrigin
@RestController
@RequestMapping("/proveedores")
public class ProvevedoresController {

    private Logger logger = Logger.getLogger(RapipagoController.class.getName());
    @Autowired
    ProvedoresService provedoresService;

    @GetMapping("/getAll")
    public ArrayList<AbmSuperDTO> getAll(){
        ver("ingresó a proveedores/getAll");
        return provedoresService.getAll(false);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ProveedorDTO proveedorDTO) {
        logger.info("Ingreso a Create proveedor " );
        HttpHeaders headers = new HttpHeaders();
        provedoresService.save(proveedorDTO);
        logger.info("Guardado " );
        return ResponseEntity.ok().body("Success");
    }
}
