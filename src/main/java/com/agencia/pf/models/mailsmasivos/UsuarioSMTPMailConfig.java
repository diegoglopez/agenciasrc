package com.agencia.pf.models.mailsmasivos;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "usuariosmtpmailconfig")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class UsuarioSMTPMailConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;
    private String usuariomail;
    private String mail;
    private String password;
    private Date fecha;
    private boolean activo;


}


