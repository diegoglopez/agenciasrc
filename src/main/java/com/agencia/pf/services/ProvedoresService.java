package com.agencia.pf.services;

import com.agencia.pf.models.abmmodels.*;
import com.agencia.pf.models.ProveedorDTO;
import com.agencia.pf.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ProvedoresService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public ArrayList<AbmSuperDTO> getAll(boolean soloActivos) {
        ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) proveedorRepository.findAll();

        ArrayList<AbmSuperDTO> provsDTO = new ArrayList<>();
        for(Proveedor prov : proveedores){
            if(soloActivos){
                if(prov.isActivo())
                        provsDTO.add(prov.toDto());
            } else
                provsDTO.add(prov.toDto());

        }


        return provsDTO;
    }

    public void save(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();
        proveedor.setDescripcion(proveedorDTO.getDescripcion());
        proveedor.setFecha(new Date());
        proveedorRepository.save(proveedor);

    }
}

