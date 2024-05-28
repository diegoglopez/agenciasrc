package com.agencia.pf.models.crucededatos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CruceDatosDTO {
    Long iddescarga;
    Integer[] proveedores;
    boolean todos;
}
