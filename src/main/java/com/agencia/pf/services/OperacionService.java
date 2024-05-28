package com.agencia.pf.services;

import com.agencia.pf.models.Operacion;
import com.agencia.pf.models.TipoOperacion;
import com.agencia.pf.repositories.OperacionRepository;
import com.agencia.pf.repositories.TipoOperacionRepository;
import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OperacionService {
    @Autowired
    OperacionRepository operacionRepository;
    @Autowired
    TipoOperacionRepository tipoOperacionRepository;

    @Autowired
    UsuarioService usuarioService;

    public Operacion crearOperacion(String tipoOperacionCodigo, String Observacion) throws Exception {

        Operacion operacion = this.crearBaseOperacion(tipoOperacionCodigo);
        operacion.setObservacion(Observacion);
        operacion = operacionRepository.save(operacion);
        return operacion;

    }

    public Operacion crearOperacion(String tipoOperacionCodigo) throws Exception {

        Operacion operacion = this.crearBaseOperacion(tipoOperacionCodigo);
        operacion = operacionRepository.save(operacion);
        return operacion;

    }

    private Operacion crearBaseOperacion(String tipoOperacionCodigo) throws Exception {
        List tipoOperaciones = tipoOperacionRepository.findByCodigo(tipoOperacionCodigo);

        if(tipoOperaciones==null || tipoOperaciones.isEmpty())
            throw new Exception("No se pudo encontrar el tipo de Operacion en la creacion de la operacion");

        Operacion operacion = new Operacion();
        operacion.setTipoOperacion((TipoOperacion) tipoOperaciones.get(0));
        operacion.setUsuario( usuarioService.getUsuarioActual());
        operacion.setComienzo(new Date());
        return operacion;
    }

    public void cerrarOpeacion(Operacion operacion){
        operacion.setFin(new Date());
        operacionRepository.save(operacion);
    }

    public void cerrarOpeacion(Operacion operacion, Integer qregistros){
        operacion.setFin(new Date());
        operacion.setRegistrosafectados(qregistros);
        operacionRepository.save(operacion);
    }


}
