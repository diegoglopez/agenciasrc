package com.agencia.pf.services;

import com.agencia.pf.models.EmailObject;
import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.mailsmasivos.IEmailService;
import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.repositories.EnvioMailsMasivosRepository;
import com.agencia.pf.repositories.ExportacionMPRespository;
import com.agencia.pf.utiles.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static com.agencia.pf.utiles.Utiles.*;

@Service
public class EnviosMailsMasivosService {

    @Autowired
    private IEmailService emailService;
    @Autowired
    EmailUserConfigService emailUserConfigService;

    @Autowired
    UsuarioService usuarioService;
    public static final Integer MAX_COL_EXCEL = 8; // Empieza desde el 1
    public void procesar(MultipartFile file, ArrayList<String> errores) throws Exception {
        ArrayList<String> listacero = new ArrayList<String>();
        Usuario usuarioActual = usuarioService.getUsuarioActual();
        UsuarioSMTPMailConfig user = emailUserConfigService.getUserMailConfig(usuarioActual.getId());
        importaExcel(() -> procesarCorreosExcel(listacero,errores,user), file, listacero,MAX_COL_EXCEL);
    }

    public void procesarCorreosExcel(ArrayList<String> listacero, ArrayList<String> errores, UsuarioSMTPMailConfig user) {
        ver("Fila: ", listacero.get(0) , " - TO: ", listacero.get(1), " - CCO: ", listacero.get(2));

        EmailObject emailObject = new EmailObject();
        emailObject.setTo(listacero.get(1));
        emailObject.setSubject("Mail prueba");
        emailObject.setBody("Mail de prueba con Spring boot");

        emailService.sendEmail(emailObject, user);

    }
}
