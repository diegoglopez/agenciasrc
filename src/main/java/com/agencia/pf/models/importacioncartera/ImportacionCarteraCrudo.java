package com.agencia.pf.models.importacioncartera;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "importacioncarteracrudo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImportacionCarteraCrudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;
    private String documento;
    private String nombre;
    private String telefono;
    private String ccategoriatelefono;
    private Integer ncategoriatelefono;
    private String razonsocial;
    private String mail;
    private Date fecha;
    private boolean importado;
    private Long idoperacion;

}
