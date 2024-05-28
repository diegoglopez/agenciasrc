package com.agencia.pf.models;

import com.agencia.pf.models.abmmodels.Proveedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "emailpersona")
@AllArgsConstructor
@NoArgsConstructor
public class EmailPersona {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String email;
    @OneToOne
    @JoinColumn(name = "idpersona", referencedColumnName = "id")
    @Getter @Setter
    private Persona persona;
    @Getter @Setter
    private boolean confirmado;
    @OneToOne
    @JoinColumn(name = "idproveedor", referencedColumnName = "id")
    @Getter @Setter
    private Proveedor proveedor;
}
