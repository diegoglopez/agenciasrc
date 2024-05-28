package com.agencia.pf.repositories;

import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmailUserConfigRepositoty extends CrudRepository<UsuarioSMTPMailConfig, Long> {

    @Query(value = "SELECT m FROM UsuarioSMTPMailConfig m WHERE m.usuario.id=:idusuario")
    List<UsuarioSMTPMailConfig> findByIdUsuario(@Param("idusuario") Integer idusuario);


}
