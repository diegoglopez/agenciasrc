package com.agencia.pf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Persona {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idtipodocumento", referencedColumnName = "id")
    private TipoDocumento tipoDocumento;
    private String documento;
    private String cuil;
    private String nombre;
    private String apellido;
    private Date fechanac;
    private boolean activo;
    private String sexo;
    @OneToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id")
    private Pais pais;
    private Date creado;
    private Date modificado;


}
