package com.agencia.pf.utiles;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Utiles {
    public static final String DIAS = "DIAS";
    public static final String DIASDELMES = "DIASDELMES";
    public static final String MES = "MES";
    public static final String ANIO = "ANIO";


    public static void ver(Object ...params) {
        String message="";
        for (Object o : params) {
            message = message + o;
        }
        System.out.println(message);
    }

    public static void importaExcel(Runnable funcion,
                                            MultipartFile file,
                                                ArrayList<String> listacero,
                                                    Integer maxSize) throws Exception {
        String nombreArchivo = file.getOriginalFilename();;
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        /*********************/
        /** En la posicion Cero se guarda la fila del Excel **/
        ver("Comenzando la importación de Excel: " + nombreArchivo);
        Integer fila=0;
        Integer columna = 0;
        try {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                fila = row.getRowNum() + 1;
                Iterator<Cell> cellIterator = row.cellIterator();
                listacero.clear();
                listacero.add(0,fila.toString());
                if(fila > 1){ // sin la cabecera
                    int j = 0;
                    while (cellIterator.hasNext()  && j < maxSize ) { // mientras tenga datos o llegue al maximo de FILAS establecida en el codigo

                        Cell cell = cellIterator.next();
                        columna = cell.getColumnIndex() + 1;
                        DataFormatter dataFormatter = new DataFormatter();
                        String value = dataFormatter.formatCellValue(cell);
                        ver("Col : " + columna + " valor: " + value + " J: " + j);
                        int currentSize = listacero.size();
                        for (int i = currentSize; i < maxSize; i++) {
                            listacero.add("");
                        }
                        if (columna <=maxSize)
                            listacero.add(columna, value);
                        j++;
                    }

                    int currentSize = listacero.size();
                    if (currentSize < maxSize) {
                        ver("Columnas vacías: " + (currentSize - listacero.size()));
                        for (int i = currentSize; i < maxSize; i++) {
                            listacero.add("SIN  DATO");
                        }
                    }
                    for(String dato : listacero){
                        ver("DATOS: " + dato);
                    }

                    funcion.run();
                }
            }
            ver("Terminó la importación de Excel  " + nombreArchivo);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger("Se produjo un error de importación en la fila: "
                                            + fila + " - Columna : " + columna + " del archivo "
                                                + nombreArchivo,  ex.getMessage());
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo ejecutar la importación de Excel");
        }
    }

    public static void importaExcel2(Runnable funcion,
                                    MultipartFile file,
                                    ArrayList<String> listacero,
                                    Integer maxSize) throws Exception {
        String nombreArchivo = file.getOriginalFilename();;
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        /*********************/
        /** En la posicion Cero se guarda la fila del Excel **/
        ver("Comenzando la importación de Excel: " + nombreArchivo);
        Integer fila=0;
        Integer columna = 0;
        try {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                fila = row.getRowNum() + 1;
                Iterator<Cell> cellIterator = row.cellIterator();
                listacero.clear();
                listacero.add(0,fila.toString());
                if(fila > 1){ // sin la cabecera
                    for (int i = 0; i < maxSize; i++)  {
                        Cell cell = row.getCell(i);
                        if (cell == null) {
                            // Si la celda no existe, agrega una cadena vacía a la lista
                            listacero.add(i +1 ,"");
                        } else {
                            // Obtiene el valor de la celda como cadena y agrega a la lista
                            columna = cell.getColumnIndex() + 1;
                            DataFormatter dataFormatter = new DataFormatter();
                            String value = dataFormatter.formatCellValue(cell);
                            listacero.add(columna,value);
                        }
                    }
                    for(String dato : listacero){
                        ver("DATOS: " + dato);
                    }
                    funcion.run();
                }
            }
            ver("Terminó la importación de Excel  " + nombreArchivo);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger("Se produjo un error de importación en la fila: "
                    + fila + " - Columna : " + columna + " del archivo "
                    + nombreArchivo,  ex.getMessage());
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo ejecutar la importación de Excel");
        }
    }

    public static void importaExcel3(Runnable funcion,
                                    MultipartFile file,
                                    String[] listacero,
                                    Integer MAX_COL_EXCEL_CRUCE) throws Exception {
        String nombreArchivo = file.getOriginalFilename();;
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        /*********************/
        /** En la posicion Cero se guarda la fila del Excel **/
        ver("Comenzando la importación de Excel: " + nombreArchivo);
        Integer fila=0;
        Integer columna = 0;
        try {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                fila = row.getRowNum() + 1;
                Iterator<Cell> cellIterator = row.cellIterator();
                // listacero = new String[MAX_COL_EXCEL_CRUCE + 1];
                listacero[0] = fila.toString();
                if(fila > 1){ // sin la cabecera
                    int j = 0;
                    while (cellIterator.hasNext()  && j < MAX_COL_EXCEL_CRUCE ) { // mientras tenga datos o llegue al maximo de FILAS establecida en el codigo

                        Cell cell = cellIterator.next();
                        columna = cell.getColumnIndex() + 1;
                        DataFormatter dataFormatter = new DataFormatter();
                        String value = dataFormatter.formatCellValue(cell);

                        if(columna != j + 1){
                            for (int i = j + 1; i < MAX_COL_EXCEL_CRUCE; i++){
                                listacero[i] = "";
                                ver("j: " + j + " Columna: " + columna);
                            }
                        }

                        if (columna <=MAX_COL_EXCEL_CRUCE)
                            listacero[columna] = value;

                        j = columna;
                    }

                    for(String dato : listacero){
                        ver("DATOS: " + dato);
                    }
                    funcion.run();
                }
            }
            ver("Terminó la importación de Excel  " + nombreArchivo);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger("Se produjo un error de importación en la fila: "
                    + fila + " - Columna : " + columna + " del archivo "
                    + nombreArchivo,  ex.getMessage());
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo ejecutar la importación de Excel");
        }
    }
    public static void showlogError(Object object, String mensaje){
        ver(mensaje);
        Logger.getLogger(object.getClass().getName()).log(Level.SEVERE, null, mensaje);
    }

    public static void showlogInfo(Object object, String mensaje){
        ver(mensaje);
        Logger.getLogger(object.getClass().getName()).log(Level.INFO, null, mensaje);
    }

    public static Date addTime(Date dateIni, String tipo, Integer number){
        Date today = dateIni;
        System.out.println(today);

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        // Adding time
        switch (tipo) {
            case ANIO:
                cal.add(Calendar.YEAR, number);
            case MES:
                cal.add(Calendar.MONTH, number);
            case DIAS:
                cal.add(Calendar.DATE, number);
            case DIASDELMES:
                cal.add(Calendar.DAY_OF_MONTH, number);

            return null;
        };
        return cal.getTime();
    }

    public static BigDecimal stringToBigDecimal(String importe){
        BigDecimal numeroBD = null;
        String numero = importe;
        try {
            numero = numero.replace(".", "");
            numero = numero.replace(",", ".");
            numero = numero.trim();
            ver("Importe antes de convertir: " + numero);
            numeroBD = new BigDecimal(numero);

        }catch (Exception e){
            ver("ERROR CREANDO IMPORTE");
            e.printStackTrace();
        }
        return numeroBD;
    }


    public static ByteArrayInputStream exportarExcelfromList(ResultSet rs) throws Exception {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Datos1");
        Row row = sheet.createRow(0);
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);

        for (int c = 0; c < rs.getMetaData().getColumnCount(); c++) {
            Cell cell = row.createCell(c);
            cell.setCellValue(rs.getMetaData().getColumnLabel(c+1));
            cell.setCellStyle(style);
        }
        int i = 1;
        while (rs.next()) {
            row = sheet.createRow(i);
            for (int f = 0; f < rs.getMetaData().getColumnCount(); f++) {
                Cell cell = row.createCell(f);
                cell.setCellValue(rs.getString(f+1));
            }
            i++;
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            ver("Error SQL Error RS: " + e.getMessage());
        }


        /*
        Sheet sheet2 = workbook.createSheet("ResumenImportacion");
        Row row2 = sheet2.createRow(0);

        Cell cell1 = row2.createCell(0);
        cell1.setCellValue("Total de Registros");
        Cell cell2 = row2.createCell(1);
        cell2.setCellValue("Suma Total");

        row2 = sheet2.createRow(1);
        row2.createCell(0).setCellValue(qTotal);
        row2.createCell(1).setCellValue(total.toString().replace(".",","));
        */
        try {
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(stream.toByteArray());
    }

    public static String encryptPassword(String password) {
        ver("Encriptando Password...");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(password);
        ver("Password encriptada!");

        return encryptedPassword;
    }



}
