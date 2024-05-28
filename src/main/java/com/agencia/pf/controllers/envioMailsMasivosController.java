package com.agencia.pf.controllers;

import com.agencia.pf.models.EmailObject;
import com.agencia.pf.models.ResponseMessage;
import com.agencia.pf.models.UsuarioDTO;
import com.agencia.pf.models.mailsmasivos.IEmailService;
import com.agencia.pf.models.mailsmasivos.MailPlantillaConfig;
import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import com.agencia.pf.models.rapipago.RapipagoResponseConsulta;
import com.agencia.pf.services.EmailUserConfigService;
import com.agencia.pf.services.EnviosMailsMasivosService;
import com.agencia.pf.services.MailPlantillaConfigService;
import com.agencia.pf.utiles.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.agencia.pf.utiles.Utiles.showlogInfo;
import static com.agencia.pf.utiles.Utiles.ver;

@CrossOrigin
@RestController
@RequestMapping("/mails")
public class envioMailsMasivosController {

    private Logger logger = Logger.getLogger(RapipagoController.class.getName());
    @Autowired
    EnviosMailsMasivosService enviosMailsMasivosService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    EmailUserConfigService emailUserConfigService;




    @GetMapping("/sendMailPrueba")
    public ResponseEntity sendMail(){
        ver("ingresó a envios de Mails");
        // mailService.sendMail();
        // emailServiceSMTP.sendEmailDos();
        EmailObject emailObject = new EmailObject();

        String toAddresses[] = {"diego9966@gmail.com", "diego_9966@hotmail.com"};
        emailObject.setLto(toAddresses);
        emailObject.setSubject("Mail prueba");
        emailObject.setBody("Mail de prueba con Spring boot");
        UsuarioSMTPMailConfig user = emailUserConfigService.getUserMailConfig(2);
        emailService.sendEmail(emailObject,user);
        return ResponseEntity.ok("Success");
    }

    /**
     * Procesamiento masivo de envío de mails
     *
     * @param file Excel
     * @return ResponseMessage
     */
    @PostMapping("/mailSender")
    public ResponseEntity<ResponseMessage> mailSender(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                ArrayList<String> errores = new ArrayList<String>();
                enviosMailsMasivosService.procesar(file,errores);
                showlogInfo(this,"Importando archivo: " + file.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, errores, null));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println("ERROR : " + e.getMessage());
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    /**
     * Crea una instancia de configuracion de mails
     * Sirve para enviar un usuario con lo parametros de configuracion de SMTP
     * que se utiliza para enviar mails
     * @param UsuarioSMTPMailConfig
     * @return ResponseEntity
     */
    @PostMapping("/createUserConfgiuration")
    public ResponseEntity create(@RequestBody UsuarioSMTPMailConfig user) {
        logger.info("Ingreso a Create User " );
        HttpHeaders headers = new HttpHeaders();
        emailUserConfigService.save(user);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/getUsuarioConfig")
    public ResponseEntity<UsuarioSMTPMailConfig> getUsuarioConfig(@RequestParam("idusuario") Integer idusuario) {
        logger.info("Ingreso a Busqueda de usuario: " + idusuario );
        HttpHeaders headers = new HttpHeaders();
        UsuarioSMTPMailConfig response = emailUserConfigService.getUserMailConfig(idusuario);
        return ResponseEntity.ok().body(response);
    }



}
