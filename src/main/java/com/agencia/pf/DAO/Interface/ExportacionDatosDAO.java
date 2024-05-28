package com.agencia.pf.DAO.Interface;

import com.agencia.pf.models.crucededatos.CruceDatosDTO;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface ExportacionDatosDAO {
    ByteArrayInputStream cruceTodosLosProveedoresExcel(CruceDatosDTO cruceDatosDTO) throws Exception;
}
