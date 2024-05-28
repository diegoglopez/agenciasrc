package com.agencia.pf.services;

import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.models.mercadopago.ExportacionMPDTO;
import com.agencia.pf.repositories.ExportacionMPRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExportacionMPService {
    @Autowired
    ExportacionMPRespository exportacionMPRespository;

    public ExportacionMP getById(Long idDescarga) {

        Optional<ExportacionMP> exportacionMP = exportacionMPRespository.findById(idDescarga);

        if(exportacionMP.isPresent())
            return exportacionMP.get();
        else
            return null;
    }

    public ArrayList<ExportacionMPDTO> getAll() {
        ArrayList<ExportacionMP> exportacionMPS = (ArrayList<ExportacionMP>) exportacionMPRespository.findAll();
        ArrayList<ExportacionMPDTO> exportacionMPSdto = new ArrayList<ExportacionMPDTO>();
        for(ExportacionMP ex : exportacionMPS)
            exportacionMPSdto.add(ex.toDto());

        return exportacionMPSdto;
    }
}
