package com.agencia.pf.repositories;

import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.mercadopago.ExportacionMP;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
