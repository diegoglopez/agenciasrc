package com.agencia.pf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "laboralpersona")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LaboralPersona {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razonsocial;
    @OneToOne
    @JoinColumn(name = "idpersona", referencedColumnName = "id")
    private Persona persona;
    private Date fecha;
    private Long idoperacion;
    private boolean activo;
}
