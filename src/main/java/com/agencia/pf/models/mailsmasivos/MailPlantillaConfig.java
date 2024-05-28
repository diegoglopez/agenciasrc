package com.agencia.pf.models.mailsmasivos;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "mailplantillaconfig")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailPlantillaConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;
    private String nombre;
    private String asunto;
    private String mensaje;
    private Date fecha;
    private boolean predeterminado;
    private boolean activo;


    public MailPlantillaConfigDTO toDTO(){
        MailPlantillaConfigDTO mailPlantillaConfigDTO = new MailPlantillaConfigDTO();
        mailPlantillaConfigDTO.setId(this.getId());
        mailPlantillaConfigDTO.setFecha(this.getFecha());
        mailPlantillaConfigDTO.setAsunto(this.getAsunto());
        mailPlantillaConfigDTO.setMensaje(this.getMensaje());
        mailPlantillaConfigDTO.setNombre(this.getNombre());
        mailPlantillaConfigDTO.setPredeterminado(this.isPredeterminado());

        return mailPlantillaConfigDTO;
    }
}
