package com.agencia.pf.controllers;

import com.agencia.pf.models.abmmodels.AbmSuper;
import com.agencia.pf.models.abmmodels.AbmSuperDTO;
import com.agencia.pf.models.ProveedorDTO;
import com.agencia.pf.models.abmmodels.IAbmSuper;
import com.agencia.pf.models.abmmodels.Proveedor;
import com.agencia.pf.services.ProvedoresService;
import com.agencia.pf.services.TipoCelularServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.agencia.pf.utiles.Utiles.ver;

@CrossOrigin
@RestController
@RequestMapping("/abm")
public class ABMController {
    private Logger logger = Logger.getLogger(RapipagoController.class.getName());
    @Autowired
    ProvedoresService provedoresService;

    @Autowired
    TipoCelularServiceImpl tipoCelularService;

    @GetMapping("/proveedores/getAll")
    public ArrayList<AbmSuperDTO> getAllProveedores(){
        ver("Ingreso a abmController/proveedores/getAll" );
        return provedoresService.getAll(false);
    }

    @GetMapping("/tiposcelulares/getAll")
    public ArrayList<AbmSuperDTO> getAllTiposProductos(){
        ver("Ingreso a abmController/tiposcelulares/getAll" );
        return tipoCelularService.getAll(false);
    }

    @PostMapping("/proveedores/create")
    public ResponseEntity create(@RequestBody ProveedorDTO proveedorDTO) {
        logger.info("Ingreso a Create proveedor " );
        HttpHeaders headers = new HttpHeaders();
        provedoresService.save(proveedorDTO);
        logger.info("Guardado " );
        return ResponseEntity.ok().body("Success");
    }
}
