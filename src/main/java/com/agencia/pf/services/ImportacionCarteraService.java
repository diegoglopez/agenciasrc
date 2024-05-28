package com.agencia.pf.services;

import com.agencia.pf.models.Operacion;
import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.repositories.CruceRepository;
import com.agencia.pf.repositories.ImportacionCarteraRepository;
import com.agencia.pf.utiles.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static com.agencia.pf.utiles.Utiles.*;

@Service
public class ImportacionCarteraService extends ServiceComunClass {

    @Autowired
    ImportacionCarteraRepository importacionCarteraRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CruceRepository cruceRepository;

    public static final Integer MAX_COL_EXCEL = 6; // Empieza desde el 1

    public void save2(MultipartFile file, ArrayList<String> errores, Long idproveedor ) throws Exception {

        Operacion operacion = crearOperacion(Constants.TIPO_OPERACION_IMPORTACION_CARTERA);
        String[] listacero = new String[MAX_COL_EXCEL + 1];
        showlogInfo(this, "Comienza el procesamiento del Excel: " + file.getOriginalFilename() + " Operacion Numero: " + operacion.getId());
        importaExcel3(() -> procesarDatosdeExcelCruce(listacero,errores,idproveedor,operacion), file, listacero,MAX_COL_EXCEL);
        ver("Ejecutando Procedure......  ");
        cruceRepository.ejecutarSPEnriquecimiento(operacion.getId().intValue());
        cerrarOperacion(operacion);
        ver("Procedure Terminado...... [OK]");
        showlogInfo(this, "Fin del procesamiento del Excel: " + file.getOriginalFilename() + " Operacion Numero: " + operacion.getId());
    }
    public void save(MultipartFile file, ArrayList<String> errores, Long idproveedor ) throws Exception {
        /***OBSOLETO**/
        Operacion operacion = crearOperacion(Constants.TIPO_OPERACION_IMPORTACION_CARTERA);
        ArrayList<String> listacero = new ArrayList<String>();
        showlogInfo(this, "Comienza el procesamiento del Excel: " + file.getOriginalFilename() + " Operacion Numero: " + operacion.getId());
        importaExcel(() -> procesarDatosdeExcelCruce(listacero,errores,idproveedor,operacion), file, listacero,MAX_COL_EXCEL);
        cerrarOperacion(operacion);
        ver("Ejecutando Procedure......  ");
        importacionCarteraRepository.ejecutarSPEnriquecimiento(operacion.getId().intValue());
        ver("Procedure Terminado...... [OK]");
    }



    private void procesarDatosdeExcelCruce(String[] listacero, ArrayList<String> errores, Long idproveedor, Operacion operacion) {

        Usuario usuario = operacion.getUsuario();
        String parteAlfabetica = (!listacero[4].equals("") )? listacero[4].replaceAll("[^A-Za-z]", "") : "Z";
        String parteNumerica = (!listacero[4].equals(""))? listacero[4].replaceAll("[^0-9]", "") : "0";
        ver("Fila: ", listacero[0] ,
                " - DNI: ", listacero[1],
                " - NOMBRE: ", listacero[2],
                " - TELEFONO: ", listacero[3],
                " - CATEGORIA: ", listacero[4] ,
                " - RAZON SOCIAL: ", listacero[5],
                " - MAIL ", listacero[6],
                " - IdProveedor: " + idproveedor);

        importacionCarteraRepository.insertarDatos(usuario.getId(),  // idusuario
                idproveedor,  // idproveedor
                listacero[1], // documento
                listacero[2], // nombre
                (parteAlfabetica!=null  && !parteAlfabetica.equals(""))? parteAlfabetica :"Z",// ccategoriatelefono
                (parteNumerica!=null && !parteNumerica.equals(""))?Integer.parseInt(parteNumerica) : 0 ,// ncategoriatelefono
                listacero[3],// telefono
                listacero[5], // razonsocial
                listacero[6],
                operacion.getId()); // mail
        showlogInfo(this,"Guardado!");

    }



    private void procesarDatosdeExcelCruce(ArrayList<String> listacero, ArrayList<String> errores, Long idproveedor, Operacion operacion) {

        Usuario usuario = operacion.getUsuario();
        String parteAlfabetica = (listacero.get(4)!=null && !listacero.get(4).equals("") )? listacero.get(4).replaceAll("[^A-Za-z]", "") : "Z";
        String parteNumerica = (listacero.get(4)!=null && !listacero.get(4).equals(""))? listacero.get(4).replaceAll("[^0-9]", "") : "0";
        ver("Fila: ", listacero.get(0) ,
                        " - DNI: ", listacero.get(1),
                        " - NOMBRE: ", listacero.get(2),
                        " - TELEFONO: ", listacero.get(3),
                        " - CATEGORIA: ", listacero.get(4),
                        " - RAZON SOCIAL: ",  listacero.get(5),
                        " - MAIL ", listacero.get(6),
                        " - IdProveedor: " + idproveedor);

        importacionCarteraRepository.insertarDatos(usuario.getId(),  // idusuario
                                        idproveedor,  // idproveedor
                                        listacero.get(1), // documento
                                        listacero.get(2), // nombre
                                        (parteAlfabetica!=null  && !parteAlfabetica.equals(""))? parteAlfabetica :"Z",// ccategoriatelefono
                                        (parteNumerica!=null && !parteNumerica.equals(""))?Integer.parseInt(parteNumerica) : 0 ,// ncategoriatelefono
                                        listacero.get(3),// telefono
                                        listacero.get(5), // razonsocial
                                        listacero.get(6),
                                        operacion.getId()); // mail
        showlogInfo(this,"Guardado!");

    }
}
