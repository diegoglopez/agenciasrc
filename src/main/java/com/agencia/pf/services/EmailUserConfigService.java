package com.agencia.pf.services;

import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.UsuarioDTO;
import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import com.agencia.pf.repositories.EmailUserConfigRepositoty;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.agencia.pf.utiles.Utiles.*;

@Service
public class EmailUserConfigService {
    @Autowired
    EmailUserConfigRepositoty emailUserConfigRepositoty;

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    private StringEncryptor encryptor;


    public void save(UsuarioSMTPMailConfig user) {
        user.setPassword(encryptMD5(user.getPassword()));
        Usuario usuario = new Usuario();
        usuario.setId(2);
        user.setUsuario(usuario);
        user.setFecha(new Date());
        user.setActivo(true);
        emailUserConfigRepositoty.save(user);
    }

    public UsuarioSMTPMailConfig getUserMailConfig(Integer idusuario) {
        List<UsuarioSMTPMailConfig> list = emailUserConfigRepositoty.findByIdUsuario(idusuario);
        UsuarioSMTPMailConfig usuarioSMTPMailConfig= null;
        if(list!=null && !list.isEmpty()){
            usuarioSMTPMailConfig = list.get(0);
            usuarioSMTPMailConfig.setPassword(desEncryptMD5(usuarioSMTPMailConfig.getPassword()));
            usuarioSMTPMailConfig.setUsuario(null);
            usuarioSMTPMailConfig.setFecha(null);
        }
        return usuarioSMTPMailConfig;
    }

    public String encryptMD5(String contrasena) {
        return encryptor.encrypt(contrasena);
    }

    public String desEncryptMD5(String contrasenaEncriptada) {
        return encryptor.decrypt(contrasenaEncriptada);
    }

}
