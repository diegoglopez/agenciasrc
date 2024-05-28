package com.agencia.pf.utiles;

import com.agencia.pf.models.mercadopago.MercadoPagoLink;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<MercadoPagoLink> mercadoPagoUpload(MultipartFile file){
        List<MercadoPagoLink> lista = new ArrayList<MercadoPagoLink>();


        return lista;
    }


}
