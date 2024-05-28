package com.agencia.pf.controllers;

import com.agencia.pf.models.UsuarioDTO;
import com.agencia.pf.models.rapipago.RapipagoConsulta;
import com.agencia.pf.models.rapipago.RapipagoResponseConsulta;
import com.agencia.pf.repositories.UsuarioRepository;
import com.agencia.pf.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsuariosController {

    private Logger logger = Logger.getLogger(RapipagoController.class.getName());
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UsuarioDTO user) {
        logger.info("Ingreso a Create User " );
        HttpHeaders headers = new HttpHeaders();
        usuarioService.save(user);
        return ResponseEntity.ok().body("Success");
    }

}
