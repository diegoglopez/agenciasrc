package com.agencia.pf.models.abmmodels;

import com.agencia.pf.models.Usuario;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbmSuper implements IAbmSuper  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "idusuariocarga", referencedColumnName = "id")
    private Usuario usuario;
    @Column(name = "activo", nullable = false)
    private boolean activo;

    public AbmSuperDTO toDto(){
        AbmSuperDTO superDTO = new AbmSuperDTO();
        superDTO.setId(this.getId());
        superDTO.setDescripcion(this.getDescripcion());
        superDTO.setFecha(this.getFecha());
        superDTO.setUsuario((this.getUsuario()!=null)?this.getUsuario().getNombre() : "");
        superDTO.setEstado(this.isActivo());

        return superDTO;
    }

}
