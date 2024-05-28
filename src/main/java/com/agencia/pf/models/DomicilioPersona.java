package com.agencia.pf.models;

import com.agencia.pf.models.abmmodels.Proveedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "domicilio_persona")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DomicilioPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idpersona", referencedColumnName = "id", nullable = false)
    @Getter @Setter
    private Persona persona;
    @Column(name = "calle", nullable = false)
    private String calle;
    @Column(name = "numero", nullable = true, columnDefinition = "varchar(10) default null")
    private String numero;
    @Column(name = "piso", nullable = true, columnDefinition = "varchar(10) default null")
    private String piso;
    @Column(name = "depto", nullable = true, columnDefinition = "varchar(10) default null")
    private String depto;
    @Column(name = "codigopostal", nullable = true, columnDefinition = "varchar(10) default null")
    private String codigopostal;
    private String localidad;
    private String ciudad;
    @OneToOne
    @JoinColumn(name = "idprovincia", referencedColumnName = "id")
    private Provincia provincia;
    @OneToOne
    @JoinColumn(name = "idpais", referencedColumnName = "id")
    private Pais pais;
    @Column(name = "fecha")
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "idproveedor", referencedColumnName = "id")
    @Getter @Setter
    private Proveedor proveedor;



}
