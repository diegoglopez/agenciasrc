package com.agencia.pf.services;


import com.agencia.pf.DAO.Interface.ExportacionDatosDAO;
import com.agencia.pf.models.Usuario;
import com.agencia.pf.models.crucededatos.CruceDatosDTO;
import com.agencia.pf.models.crucededatos.ImportacionDatosCruce;
import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.repositories.CruceRepository;
import com.agencia.pf.repositories.ExportacionMPRespository;
import com.agencia.pf.repositories.ImportacionDatosCruceRepository;
import com.agencia.pf.utiles.Constants;
import com.agencia.pf.utiles.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import com.agencia.pf.repositories.ImportacionMercadoPagoRepository;
import com.agencia.pf.utiles.ExcelHelper;
import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static com.agencia.pf.utiles.Utiles.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImportacionExcelService {
    @Autowired
    ImportacionMercadoPagoRepository importacionMercadoPagoRepository;

    @Autowired
    ExportacionMPRespository exportacionMPRespository;

    @Autowired
    ImportacionDatosCruceRepository importacionDatosCruceRepository;

    @Autowired
    CruceRepository cruceRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ExportacionDatosDAO exportacionDatosDAO;

    public static final Integer LINK_DIAS_VENCIMIENTO = 31;
    public static final String COMPANIA_CFF = "CFF";
    public static final String COMPANIA_CSA = "CSA";
    public static final Integer MAX_COL_EXCEL = 8; // Empieza desde el 1
    public static final Integer MAX_COL_EXCEL_CRUCE = 2; // Empieza desde el 1
    // Entre 1 y 30 dias maximo : https://www.mercadopago.com.co/developers/es/guides/online-payments/checkout-pro/configurations#editor_3


    public void executeStoredProcedure() {
        jdbcTemplate.execute("CALL spCruceUno()");
    }
    public void executeStoredProcedureWithParam(Integer id) {
//        jdbcTemplate.call("{CALL nombre_del_stored_procedure(?)}",
//                    Collections.singletonList(new SqlParameter("vIddivision", Types.VARCHAR)));

        cruceRepository.callPrecudreCruce(id);
    }

    public ByteArrayInputStream descargarCruceDeDatos(CruceDatosDTO cruceDatosDTO) throws Exception {
        ver("INGRESO : Service descargarCruceDeDatos ");
        try {
            return exportacionDatosDAO.cruceTodosLosProveedoresExcel(cruceDatosDTO);
        } catch (Exception e) {
            ver("No se pudo  generar descargarCruceDeDatos " + e.getMessage());
            throw e;
        }

    }


    public Long save(MultipartFile file, ArrayList<String> errores , Long idDescarga) throws Exception {

        ArrayList<String> listacero = new ArrayList<String>();
        Usuario usuario = new Usuario();
        usuario.setId(2); // HARDCODING - Usuario de CARO
        ExportacionMP exportacionMP = new ExportacionMP();
        exportacionMP.setDescripcion(file.getOriginalFilename());
        exportacionMP.setUsuario(usuario);
        exportacionMPRespository.save(exportacionMP);
        idDescarga=exportacionMP.getId();
        showlogInfo(this, "IDDESCARGA: " + idDescarga);
        importaExcel(() -> ejecucion(listacero,errores,exportacionMP.getId()), file, listacero,MAX_COL_EXCEL);
        return exportacionMP.getId();
    }
    /**
     * Tratamiento de Excel para la importacion del cruce con la base de datos
     * Toma el Excel lo guarda y realiza un cruce con los datos guardados en los enriquecimientos
     * Devuelve una lista de errores en la importacion o OK
     *
     * */
    public Long saveExcelCruce(MultipartFile file, ArrayList<String> errores , Long idDescarga) throws Exception {

        ArrayList<String> listacero = new ArrayList<String>();
        Usuario usuario = new Usuario();
        usuario.setId(1); // HARDCODING - Usuario de CARO
        ExportacionMP exportacionMP = new ExportacionMP();
        exportacionMP.setDescripcion(file.getOriginalFilename());
        exportacionMP.setUsuario(usuario);
        exportacionMPRespository.save(exportacionMP);
        idDescarga=exportacionMP.getId();
        showlogInfo(this, "IDDESCARGA: " + idDescarga);
        showlogInfo(this,"COMIENZO DE LA IMPORTACION DE DATOS PARA EL CRUCE!" + file.getOriginalFilename());
        importaExcel(() -> procesarDatosdeExcelCruce(listacero,errores,exportacionMP.getId()), file, listacero,MAX_COL_EXCEL_CRUCE);
        showlogInfo(this,"FIN DE LA IMPORTACION DE DATOS PARA EL CRUCE!" + file.getOriginalFilename());
        return exportacionMP.getId();
    }

    public Long saveExcelCruce2(MultipartFile file, ArrayList<String> errores , Long idDescarga) throws Exception {

        String[] listacero = new String[MAX_COL_EXCEL_CRUCE + 1];
        Usuario usuario = new Usuario();
        usuario.setId(1); // HARDCODING - Usuario de CARO
        ExportacionMP exportacionMP = new ExportacionMP();
        exportacionMP.setDescripcion(file.getOriginalFilename());
        exportacionMP.setUsuario(usuario);
        exportacionMPRespository.save(exportacionMP);
        idDescarga=exportacionMP.getId();
        showlogInfo(this, "IDDESCARGA: " + idDescarga);
        showlogInfo(this,"COMIENZO DE LA IMPORTACION DE DATOS PARA EL CRUCE!" + file.getOriginalFilename());
        importaExcel3(() -> procesarDatosdeExcelCruce(listacero,errores,exportacionMP.getId()), file, listacero,MAX_COL_EXCEL_CRUCE);
        showlogInfo(this,"FIN DE LA IMPORTACION DE DATOS PARA EL CRUCE!" + file.getOriginalFilename());
        return exportacionMP.getId();
    }

    private void procesarDatosdeExcelCruce(ArrayList<String> listacero, ArrayList<String> errores, Long idDescarga) {
        ver("Fila: ", listacero.get(0) , " - DNI: ", listacero.get(1), " - NOMBRE: ", listacero.get(2));
        importacionDatosCruceRepository.insertarDatos(listacero.get(1), listacero.get(2), idDescarga);
//        importacionDatosCruceRepository.save(cruce);
        showlogInfo(this,"Guardado!");

    }

    private void procesarDatosdeExcelCruce(String[] listacero, ArrayList<String> errores, Long idDescarga) {
        ver("Fila: ", listacero[0] , " - DNI: ", listacero[1], " - NOMBRE: ", listacero[2]);

        importacionDatosCruceRepository.insertarDatos(listacero[1], listacero[2], idDescarga);
        showlogInfo(this,"Guardado!");

    }

    public void ejecucion(ArrayList<String> listacero, ArrayList<String> errores, Long idDescarga) {
        ver("Fila: ", listacero.get(0) , " - DNI: ", listacero.get(1), " - NOMBRE: ", listacero.get(2));
        MercadoPagoLink mp = new MercadoPagoLink();
        try {

            mp.setDocumento(listacero.get(1).trim());
            mp.setNombre(listacero.get(2).trim());

            mp.setCompania(setIdcompania(listacero.get(4).trim()));
            String codigoReferenciaExterna ="" ;
            ver("Antes de convertir: " + listacero.get(5));
            Integer idTipoPago=Integer.parseInt(listacero.get(6));
            codigoReferenciaExterna = this.armarLinkReferenciaExterna(listacero.get(5),idTipoPago);
            mp.setCodigo(codigoReferenciaExterna);
            ver("Despues de convertir: " + mp.getCodigo());
            mp.setIdTipoPago(Integer.parseInt(listacero.get(6)));
            mp.setMail((listacero.size() <= 7)? "" :  listacero.get(7));
            mp.setCartera((listacero.size() <= 8)? "" : listacero.get(8));
            mp.setEntidad((listacero.size() <= 9)? "" : listacero.get(9));
            mp.setDescripcion((listacero.size() <= 10)? "" : listacero.get(10));

            ExportacionMP exportacionMP = new ExportacionMP();
            exportacionMP.setId(idDescarga);
            mp.setExportacionMP(exportacionMP);
            ver("Importe " + listacero.get(3).trim());
            BigDecimal importe = stringToBigDecimal(listacero.get(3).trim());
            if(importe!=null){
                mp.setImporte(importe);
                setLinkMP(mp); // Generacion de Link de Mercado Pago
                showlogInfo(this,"Guardando... ");
                // Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, );
            } else {
                errores.add(Constants.ERROR_GENERANDO_IMPORTE + "Fila : " + listacero.get(0));
                new Exception("No se pudo generar el link : Fila --> " + listacero.get(0));
            }
            importacionMercadoPagoRepository.save(mp);
            showlogInfo(this,"Guardado!");

        }catch (Exception ex){
            ver("ERROR AL GUARDAR: " + ex);
            ex.printStackTrace();
            errores.add(Constants.ERROR_GUARDANDO_TABLA_MP + "Fila : " + listacero.get(0));
            showlogError(this,ex.getMessage());
        }

    }
    private Integer setIdcompania(String compania) {
        if(compania.equals(COMPANIA_CFF)){
            return 2;
        } else
            return 1;

    }

    private void setLinkMP (MercadoPagoLink mp) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = new Gson();
        JsonElement link = null;
        String ClientId = null;
        String ClientSecret = null;
        String codigo = "01"; // ver CODIGO CON CREDITIA
        String moneda = "ARS";
        float monto = mp.getImporte().floatValue();
        Preference preference = new Preference();
        try {

            MercadoPago.SDK.cleanConfiguration();
            if (mp.getCompania() == 2) {
                // Creditia CFF
                ClientId = "3769065669748226";
                ClientSecret = "JvDRnWIBd8yr1R791kMp81p9gXzpH1wU";

            } else {
                // Creditia CSa
                ClientId = "6444089558382634";
                ClientSecret = "ckR4yaSatdPuca0woM1YUR4Z8Wn974Hb";

            }

            /* DIEGO
            ClientId = "8213051579547778";
            ClientSecret = "B2OmXWQMIR0TVd2o108tl1LsIn8PuK6l";
            */
            MercadoPago.SDK.setClientId(ClientId);
            MercadoPago.SDK.setClientSecret(ClientSecret);

            Item item = new Item();
            item.setId(mp.getCodigo())
                    .setTitle("Abono de Deuda Via Contacto")
                    .setQuantity(1)
                    .setDescription(mp.getDescripcion())
                    .setCurrencyId(moneda)
                    .setUnitPrice(monto);

            Payer payer = new Payer();
            payer.setName(mp.getNombre());
            payer.setEmail(mp.getMail());
            Identification identification = new Identification();
            identification.setType("DNI/CUIT");
            identification.setNumber(mp.getDocumento());
            payer.setIdentification(identification);

            preference.setPayer(payer);

            // Ver si quiere que expiren
            //preference.setExpires(true);
            //preference.setExpirationDateFrom(new Date());
            // Date fechaExpiracion = new Date(System.currentTimeMillis() + LINK_DIAS_VENCIMIENTO * 24 * 60 * 60 * 1000);

            //ver(fechaExpiracion);
            //preference.setExpirationDateTo(fechaExpiracion);
            preference.appendItem(item);
//            preference.setOperationType(Preference.OperationType.recurring_payment);
            preference.setExternalReference(codigo);

            preference.save();

            link = gson.toJsonTree(preference);
            ver(link);
            mp.setLinkMP(preference.getInitPoint());
        } catch (Exception ex) {
            ex.printStackTrace();
            showlogError(this,ex.getMessage());
        }

    }



    public String armarLinkReferenciaExterna(String codigo, Integer idTipoPago) {
		 String[] arrayUno ;
		 arrayUno=codigo.split(","); // Lo convierto en Array
		 arrayUno[3] = idTipoPago.toString(); // Le agrego el idtipopago

		 String retorno="";
		 for(int i = 0; i < arrayUno.length;i++) // Lo transformo a String
		    retorno = retorno.concat(((retorno.equals(""))? "" : ",") + arrayUno[i]);

		 return retorno;
    }


    public ByteArrayInputStream descargar(Long  idDescarga) throws Exception {
        System.out.println("INGRESO : Service ");
        Optional<ArrayList<MercadoPagoLink>> mercadoPagoLinks;
        mercadoPagoLinks = importacionMercadoPagoRepository.finByIdExportacion(idDescarga);
        if(mercadoPagoLinks.isPresent()) {
            System.out.println("INGRESO : Is Present ");
            return exportarExcel(mercadoPagoLinks.get());
        } else
            throw new Exception("No se encontraron casos con el idDescarga enviado");
    }

    public ByteArrayInputStream exportarExcel(ArrayList<MercadoPagoLink> lista){

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Hoja1");
        Row row = sheet.createRow(0);


        String[] columnas = {"DNI", "NOMBRE", "IMPORTE","COMPANIA","IDTIPOPAGO", "CODIGO",
                            "MAIL",  "CARTERA",  "ENTIDAD", "OBSERVACION", "LINK_MP" };

        for(int i=0; i < columnas.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        BigDecimal total = new BigDecimal(0.0);
        Integer qTotal = 0;

        int initRow = 1;
        for(MercadoPagoLink mp : lista){
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(mp.getDocumento());
            row.createCell(1).setCellValue(mp.getNombre());
            row.createCell(2).setCellValue(mp.getImporte().toString().replace(".",","));
            row.createCell(3).setCellValue((mp.getCompania()==2)? COMPANIA_CFF : COMPANIA_CSA);
            row.createCell(4).setCellValue(mp.getIdTipoPago());
            row.createCell(5).setCellValue(mp.getCodigo());
            row.createCell(6).setCellValue(mp.getMail());
            row.createCell(7).setCellValue(mp.getCartera());
            row.createCell(8).setCellValue(mp.getEntidad());
            row.createCell(9).setCellValue(mp.getDescripcion());
            row.createCell(10).setCellValue(mp.getLinkMP());
            initRow++;
            qTotal++;
            total = total.add(mp.getImporte());
        }

        Sheet sheet2 = workbook.createSheet("ResumenImportacion");
        Row row2 = sheet2.createRow(0);

        Cell cell1 = row2.createCell(0);
        cell1.setCellValue("Total de Registros");
        Cell cell2 = row2.createCell(1);
        cell2.setCellValue("Suma Total");

        row2 = sheet2.createRow(1);
        row2.createCell(0).setCellValue(qTotal);
        row2.createCell(1).setCellValue(total.toString().replace(".",","));

        try {
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(stream.toByteArray());
    }

}
