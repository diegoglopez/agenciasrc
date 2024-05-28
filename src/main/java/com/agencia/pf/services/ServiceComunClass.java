package com.agencia.pf.services;

import com.agencia.pf.models.Operacion;
import com.agencia.pf.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceComunClass {
    @Autowired
    OperacionService operacionService;

    @Autowired
    UsuarioService usuarioService;


    public Usuario getUsuarioActual(){
        return usuarioService.getUsuarioActual();
    }

    public Operacion crearOperacion(String tipoOperacionCodigo, String Observacion) throws Exception {
        return operacionService.crearOperacion(tipoOperacionCodigo,Observacion);
    }

    public Operacion crearOperacion(String tipoOperacionCodigo) throws Exception {
        return operacionService.crearOperacion(tipoOperacionCodigo);
    }

    public void cerrarOperacion(Operacion operacion){
        operacionService.cerrarOpeacion(operacion);
    }

    public void cerrarOperacion(Operacion operacion, Integer qoperaciones){
        operacionService.cerrarOpeacion(operacion, qoperaciones);
    }

}
