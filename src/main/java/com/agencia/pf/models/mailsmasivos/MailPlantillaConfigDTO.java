package com.agencia.pf.models.mailsmasivos;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailPlantillaConfigDTO {

    private Long id;
    private String nombre;
    private String asunto;
    private String mensaje;
    private Date fecha;
    private boolean predeterminado;

}
