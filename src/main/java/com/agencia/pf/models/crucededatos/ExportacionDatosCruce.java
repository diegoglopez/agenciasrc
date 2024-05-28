package com.agencia.pf.models.crucededatos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exportaciondatoscruce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExportacionDatosCruce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idpesona;
    private String documento;
    private String nombre;
    private String telefono;
    private Long scoreTelefono;
    private String proveedorTelefono;
    private String email;
    private String proveedorEmail;


}
