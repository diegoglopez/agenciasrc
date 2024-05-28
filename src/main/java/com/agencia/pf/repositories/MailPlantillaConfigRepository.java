package com.agencia.pf.repositories;


import com.agencia.pf.models.mailsmasivos.MailPlantillaConfig;
import com.agencia.pf.models.mailsmasivos.UsuarioSMTPMailConfig;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface MailPlantillaConfigRepository extends CrudRepository<MailPlantillaConfig, Long> {
    @Modifying
    @Query( "UPDATE MailPlantillaConfig m SET m.predeterminado=false WHERE m.usuario.id=?1")
    void updatePredeterminadoByUsuario(Integer idusuario);

    @Query(value = "SELECT m FROM MailPlantillaConfig m WHERE m.usuario.id=?1 AND m.activo=true ORDER BY m.id DESC")
    List findByIdUsuario(Integer idusuario);

}
