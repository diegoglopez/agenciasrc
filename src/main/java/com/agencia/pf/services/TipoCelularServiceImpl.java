package com.agencia.pf.services;

import com.agencia.pf.models.abmmodels.AbmSuperDTO;
import com.agencia.pf.models.abmmodels.Proveedor;
import com.agencia.pf.models.abmmodels.TipoCelular;
import com.agencia.pf.repositories.TipoCelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TipoCelularServiceImpl {

    @Autowired
    TipoCelularRepository tipoCelularRepository;

    public ArrayList<AbmSuperDTO> getAll(boolean soloActivos) {
        ArrayList<TipoCelular> datos = (ArrayList<TipoCelular>) tipoCelularRepository.findAll();

        ArrayList<AbmSuperDTO> datosDTO = new ArrayList<>();
        for(TipoCelular dato : datos){
            if(soloActivos){
                if(dato.isActivo())
                    datosDTO.add(dato.toDto());
            } else
                datosDTO.add(dato.toDto());

        }
        return datosDTO;
    }
}
