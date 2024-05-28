package com.agencia.pf.controllers;

import com.agencia.pf.models.mercadopago.ExportacionMPDTO;
import com.agencia.pf.services.ExportacionMPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import static com.agencia.pf.utiles.Utiles.ver;

@CrossOrigin
@RestController
@RequestMapping("/exportaciones")
public class ExportacionMPController {
    @Autowired
    ExportacionMPService exportacionMPService;

    @GetMapping("/getAll")
    public ArrayList<ExportacionMPDTO> getAll(){
        ver("ingresó a exportaciones");
        return exportacionMPService.getAll();
    }

}
