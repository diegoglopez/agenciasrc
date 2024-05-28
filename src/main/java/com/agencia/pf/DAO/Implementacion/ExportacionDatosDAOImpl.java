package com.agencia.pf.DAO.Implementacion;

import com.agencia.pf.DAO.Interface.ExportacionDatosDAO;
import com.agencia.pf.models.crucededatos.CruceDatosDTO;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.List;

import static com.agencia.pf.utiles.Utiles.exportarExcelfromList;
import static com.agencia.pf.utiles.Utiles.ver;

@Repository
public class ExportacionDatosDAOImpl implements ExportacionDatosDAO {
    @Autowired
    private EntityManager entityManager;

    //private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;
    @Autowired
    public ExportacionDatosDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    ResultSet rs = null;

    @Override
    public ByteArrayInputStream cruceTodosLosProveedoresExcel(CruceDatosDTO cruceDatosDTO) throws Exception {
        ByteArrayInputStream datos =null;
        Integer[] idsproveedores = cruceDatosDTO.getProveedores();
        Integer cant = idsproveedores.length;
        String and="";
        if(!cruceDatosDTO.isTodos()){
            and="              WHERE idproveedor in (";
            int i = 0;
            while (i < cant){
                and = and + "?,";
                i++;
            }
            and=and.substring(0, and.length() - 1);
            and = and + ") \n";
        }

        String query =
                "SELECT  COALESCE(c.idpersona, \"Sin datos\") IDPERSONA, a.documento DOCUMENTO, a.nombre NOMBRE, tc.descripcion TIPOCELULAR,\n" +
                        "          CONCAT(COALESCE(c.codarea, \"\"), c.numero) TELEFONO, CONCAT(c.ccategoriaprioridad, c.prioridad) SCORE_TELEFONO, IF(c.confirmado,\"SI\",\"NO\") CONFIRMADO,\n" +
                        "            d.descripcion PROVEEDOR_TELEFONO, e.email EMAIL\n" +
                        "  FROM importaciondatoscruce a\n" +
                        "    LEFT JOIN persona b\n" +
                        "      ON a.documento = b.documento\n" +
                        "    LEFT JOIN (SELECT *\n" +
                        "                 FROM telefonopersona a \n" +
                        and  +
                        "               )  c\n" +
                        "      ON b.id = c.idpersona\n" +
                        "    LEFT JOIN tipocelular tc\n" +
                        "      ON c.idtipocelular=tc.id\n" +
                        "    LEFT JOIN proveedor d\n" +
                        "      ON c.idproveedor=d.id\n" +
                        "    LEFT JOIN emailpersona e\n" +
                        "      ON b.id = e.idpersona\n" +
                        "  WHERE a.idexportacion=?\n" +
                        "  ORDER BY a.id, c.idproveedor, c.ccategoriaprioridad , c.prioridad;";

        try (Connection connection = dataSource.getConnection() ;
                PreparedStatement ps = connection.prepareStatement(query)) {
            // try-with-resources cierra automaticamente los recursos que tiene como parametro
            if(!cruceDatosDTO.isTodos()){
                int c = 1;
                int d = 0;
                while (d < cant){
                    ver("ids: " + idsproveedores[d]);
                    ps.setInt(c, idsproveedores[d]);
                    c++;
                    d++;
                }
                ps.setInt(c, cruceDatosDTO.getIddescarga().intValue());
            } else {
                ps.setInt(1, cruceDatosDTO.getIddescarga().intValue());
            }
            //QUERY DE CRUCE CON LOS DATOS
            ver("Query: " + ps.toString());

            ResultSet rs = ps.executeQuery();
            // MANEJOS DE LOS DATOS DE EXCEL / EXPORTA LO QUE PASES EN EL RESULTSET
            datos = exportarExcelfromList(rs);

            // Cerrar el ResultSet
            if (rs != null) {
                ver("Conexion cerrada RS 1");
                rs.close();
            }

        } catch (SQLException e) {
            ver("SQL Exception 1 : " + e.getMessage());
            throw new Exception(e.getMessage());

        } catch (Exception e) {
            ver("Exception : " + e.getMessage());
            throw e;
        } finally {
            try {
                if (rs != null) {
                    ver("Conexion cerrada RS 2");
                    rs.close();
                }
            } catch (SQLException e) {
                ver("No se pudo cerrar la conexion del RS SQL Exception : " + e.getMessage());
                throw new Exception(e.getMessage());
            }
        }
        return datos;
    }

}
