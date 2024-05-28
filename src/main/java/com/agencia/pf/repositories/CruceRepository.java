package com.agencia.pf.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.Types;
import java.util.Map;

import static com.agencia.pf.utiles.Utiles.ver;

@Repository
public class CruceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void callPrecudreCruce (Integer id){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("bdata")
                .withProcedureName("spCruce")
                .declareParameters(
                        new SqlParameter("videxportacion", Types.INTEGER)
                );
        ver("Comienzo de ejecucion del procedure spCruce  Terminado");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("videxportacion", id);
        Map<String,Object> resultado = simpleJdbcCall.execute(mapSqlParameterSource);
        ver("Procedure spCruce Terminado");
    }

    public void ejecutarSPEnriquecimiento(Integer vidoperacion){
        jdbcTemplate.update("CALL spInsercionDatosEnriq(?)", vidoperacion);
    }
}
