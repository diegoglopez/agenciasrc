package com.agencia.pf.services;

import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.mailsmasivos.MailPlantillaConfig;
import com.agencia.pf.models.mailsmasivos.MailPlantillaConfigDTO;
import com.agencia.pf.repositories.MailPlantillaConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MailPlantillaConfigService {

    @Autowired
    MailPlantillaConfigRepository mailPlantillaConfigRepository;

    @Autowired
    UsuarioService usuarioService;
    public void save(MailPlantillaConfig mailPlantillaConfig){

        Usuario usuario = usuarioService.getUsuarioActual();
        mailPlantillaConfig.setUsuario(usuario);
        mailPlantillaConfig.setFecha(new Date());
        mailPlantillaConfig.setPredeterminado(true);
        mailPlantillaConfig.setActivo(true);
        mailPlantillaConfigRepository.updatePredeterminadoByUsuario(usuario.getId().intValue());
        mailPlantillaConfigRepository.save(mailPlantillaConfig);


    }

    public List finAllByIdUsuario(Integer idusuario) {

        List<MailPlantillaConfig> lista  = (List<MailPlantillaConfig>) mailPlantillaConfigRepository.findByIdUsuario(idusuario);
        List<MailPlantillaConfigDTO> listaDTO  = new ArrayList<MailPlantillaConfigDTO>();

        for (MailPlantillaConfig plantilla : lista)
            listaDTO.add(plantilla.toDTO());

        return listaDTO;
    }
}
