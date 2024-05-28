package com.agencia.pf.models.mailsmasivos;
import com.agencia.pf.models.EmailObject;
import org.springframework.lang.Nullable;
public interface IEmailService {
    void sendEmail(EmailObject emailObject, UsuarioSMTPMailConfig usuarioSMTPMailConfig);


}
