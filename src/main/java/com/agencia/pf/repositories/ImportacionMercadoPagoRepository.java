package com.agencia.pf.repositories;


import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Optional;
@Repository
public interface ImportacionMercadoPagoRepository extends CrudRepository<MercadoPagoLink, Long> {

    @Query(value = "SELECT m FROM MercadoPagoLink m WHERE m.exportacionMP.id=:idexportacion")
    Optional<ArrayList<MercadoPagoLink>> finByIdExportacion(@Param("idexportacion") Long idDescarga);


}
