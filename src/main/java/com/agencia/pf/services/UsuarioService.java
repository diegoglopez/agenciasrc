package com.agencia.pf.services;

import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.UsuarioDTO;
import com.agencia.pf.repositories.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.agencia.pf.utiles.Utiles.*;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void save(UsuarioDTO userDTO){

        Usuario usuario = new Usuario();
        usuario.setUsuario(userDTO.getUsuario());
        usuario.setNombre(userDTO.getNombre());
        usuario.setMail(userDTO.getMail());
        usuario.setFechaAlta(new Date());
        usuario.setFechaPass(new Date());
        usuario.setIdpais(1);
        usuario.setIdrol(1);
        usuario.setPass(encryptPassword(userDTO.getPass()));
        usuario.setBloqueado(false);
        usuario.setLoggeado(false);
        usuario.setActivo(true);
        usuario.setQIntentosPass(0);


        usuarioRepository.save(usuario);
        ver("Usuario Creado! ");

    }

    public Usuario getUsuarioActual (){
        Long valor = Long.valueOf(2);
        Optional<Usuario> usuario = null;
        usuario = usuarioRepository.findById(valor);
        return (usuario.isPresent())? usuario.get() : null;
    }

}
