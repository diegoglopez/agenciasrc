package com.agencia.pf.models.crucededatos;

import com.agencia.pf.models.mercadopago.ExportacionMP;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "importaciondatoscruce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImportacionDatosCruce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String nombre;
    @OneToOne
    @JoinColumn(name = "idexportacion", referencedColumnName = "id")
    private ExportacionMP exportacionMP;

}
