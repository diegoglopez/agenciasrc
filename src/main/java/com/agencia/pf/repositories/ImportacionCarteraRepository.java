package com.agencia.pf.repositories;

import com.agencia.pf.models.importacioncartera.ImportacionCarteraCrudo;
import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Types;
import java.util.Map;

import static com.agencia.pf.utiles.Utiles.ver;

@Transactional
@Repository
public interface ImportacionCarteraRepository  extends CrudRepository<ImportacionCarteraCrudo, Long> {


    @Modifying
    @Query(value = "INSERT INTO importacioncarteracrudo (" +
            "   idusuario\n" +
            "  ,idproveedor\n" +
            "  ,documento\n" +
            "  ,nombre\n" +
            "  ,ccategoriatelefono\n" +
            "  ,ncategoriatelefono\n" +
            "  ,telefono\n" +
            "  ,razonsocial\n" +
            "  ,mail\n" +
            "  ,fecha\n" +
            "  ,importado" +
            "  ,idoperacion) " +
            " VALUES (:idusuario\n" +
            "   ,:idproveedor\n" +
            "  ,:documento\n" +
            "  ,:nombre\n" +
            "  ,:ccategoriatelefono\n" +
            "  ,:ncategoriatelefono\n" +
            "  ,:telefono\n" +
            "  ,:razonsocial\n" +
            "  ,:mail\n" +
            "  ,curdate()\n" +
            "  ,0" +
            " ,:idoperacion\n)"
            , nativeQuery = true)
    void insertarDatos(
                            @Param("idusuario") Integer idusuario,
                            @Param("idproveedor") Long idproveedor,
                            @Param("documento") String documento,
                            @Param("nombre") String nombre,
                            @Param("ccategoriatelefono")  String ccategoriatelefono,
                            @Param("ncategoriatelefono") Integer ncategoriatelefono,
                            @Param("telefono") String telefono,
                            @Param("razonsocial") String razonsocial,
                            @Param("mail") String mail,
                            @Param("idoperacion") Long idoperacion
                        );
    @Query(value = "CALL spInsercionDatosEnriq(:idoperacion)", nativeQuery = true)
    void ejecutarSPEnriquecimiento(@Param("idoperacion") Integer idoperacion);



}
