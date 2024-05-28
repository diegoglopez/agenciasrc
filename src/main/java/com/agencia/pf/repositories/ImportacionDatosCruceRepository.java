package com.agencia.pf.repositories;

import com.agencia.pf.models.crucededatos.ImportacionDatosCruce;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ImportacionDatosCruceRepository extends CrudRepository<ImportacionDatosCruce, Long> {

    @Modifying
    @Query(value = "INSERT INTO importaciondatoscruce (documento, nombre, idexportacion) VALUES (:documento, :nombre, :idexportacion)", nativeQuery = true)
    void insertarDatos(@Param("documento") String documento, @Param("nombre") String nombre, @Param("idexportacion") Long idexportacion);

}
