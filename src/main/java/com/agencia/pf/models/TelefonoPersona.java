package com.agencia.pf.models;

import com.agencia.pf.models.abmmodels.Proveedor;
import com.agencia.pf.models.abmmodels.TipoCelular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "telefonopersona")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TelefonoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idpersona", referencedColumnName = "id", nullable = false)
    @Getter @Setter
    private Persona persona;
    @OneToOne
    @JoinColumn(name = "idtipocelular", referencedColumnName = "id")
    @Getter @Setter
    private TipoCelular tipoCelular;
    @Column(name = "codarea")
    private String codArea;
    @Column(name = "numero", nullable = false)
    private String numero;
    private String ccategoriaprioridad;
    private Long prioridad;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "confirmado", columnDefinition = "bit(1) default false")
    private boolean confirmado;
    @OneToOne
    @JoinColumn(name = "idproveedor", referencedColumnName = "id")
    @Getter @Setter
    private Proveedor proveedor;
    @ManyToMany
    @JoinTable(
            name = "otrosproveedorestelefono",
            joinColumns = @JoinColumn(name = "idtelefonopersona"),
            inverseJoinColumns = @JoinColumn(name = "idproveedor"))
    Set<Proveedor> otrosProveedores;

}
