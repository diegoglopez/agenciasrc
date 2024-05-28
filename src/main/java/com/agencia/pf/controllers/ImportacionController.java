package com.agencia.pf.controllers;


import com.agencia.pf.models.crucededatos.CruceDatosDTO;
import com.agencia.pf.models.mercadopago.ExportacionMPDTO;
import com.agencia.pf.models.ResponseMessage;
import com.agencia.pf.models.mercadopago.ExportacionMP;
import com.agencia.pf.services.ExportacionMPService;
import com.agencia.pf.services.ImportacionExcelService;
import com.agencia.pf.utiles.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.agencia.pf.utiles.Utiles.showlogInfo;
import static com.agencia.pf.utiles.Utiles.ver;

@CrossOrigin
@RestController
@RequestMapping("/importacion")
public class ImportacionController {
    @Autowired
    ImportacionExcelService importacionService;

    @Autowired
    ExportacionMPService exportacionMPService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                ArrayList<String> errores = new ArrayList<String>();
                Long idDescarga=null;
                showlogInfo(this,"Importando archivo: " + file.getOriginalFilename());
                // System.out.println("Importando archivo: " + file.getOriginalFilename());
                idDescarga=importacionService.save(file,errores, idDescarga);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, errores, idDescarga));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println("ERROR : " + e.getMessage());
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/downLoadLinks")
    public ResponseEntity<InputStreamResource> download(@RequestParam("iddescarga") Long idDescarga) throws Exception {

        ExportacionMP exportacionMP = exportacionMPService.getById(idDescarga);

        if(exportacionMP==null){
            throw new Exception("No se encontraron Datos con el ID DE DESCARGA" + idDescarga);
        }

        ByteArrayInputStream stream = null;
        try {
            stream = importacionService.descargar(idDescarga);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + exportacionMP.getDescripcion().replaceAll(".xlsx","") + "_" + exportacionMP.getFechacarga() + ".xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

    }

    @PostMapping("/uploadcruce")
    public ResponseEntity<ResponseMessage> uploadFileCruce(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                ArrayList<String> errores = new ArrayList<String>();
                Long idDescarga=null;
                showlogInfo(this,"Importando archivo: " + file.getOriginalFilename());
                // System.out.println("Importando archivo: " + file.getOriginalFilename());
                idDescarga=importacionService.saveExcelCruce2(file,errores, idDescarga);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, errores, idDescarga));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println("ERROR : " + e.getMessage());
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/sp")
    public void ejecutarSP(@RequestParam("id") Integer idDescarga){
        ver("ingresó a sp");
        importacionService.executeStoredProcedure();
    }

    @GetMapping("/spdos")
    public void ejecutarSPDOS(@RequestParam("id") Integer id){
        ver("ingresó a sp");
        importacionService.executeStoredProcedureWithParam(id);
    }

    @PostMapping("/downloadCruce")
    public ResponseEntity<InputStreamResource> downloadCruce(@RequestBody CruceDatosDTO cruceDatosDTO) throws Exception {

        ver("INGRESO A downloadCruce" + cruceDatosDTO.getIddescarga() + " todos: " + cruceDatosDTO.isTodos());
        ExportacionMP exportacionMP = exportacionMPService.getById(cruceDatosDTO.getIddescarga());
        if(exportacionMP==null){
            throw new Exception("No se encontraron Datos con el ID DE DESCARGA" + cruceDatosDTO.getIddescarga());
        }

        ByteArrayInputStream stream = null;
        try {
            stream = importacionService.descargarCruceDeDatos(cruceDatosDTO);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + exportacionMP.getDescripcion().replaceAll(".xlsx","") + "_" + exportacionMP.getFechacarga() + ".xlsx");
        ver("Termino la exportacion " + cruceDatosDTO.getIddescarga());
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

    }

    @GetMapping("/getDownloadCruce")
    public ResponseEntity<InputStreamResource> getDownloadCruce(@RequestParam("iddescarga") Long idDescarga, @RequestParam("lista") Integer[] proveedores, @RequestParam("todos") boolean todos) throws Exception {

        ver("INGRESO A downloadCruce" +idDescarga + " todos: " + todos);
        CruceDatosDTO cruceDatosDTO = new CruceDatosDTO();
        cruceDatosDTO.setIddescarga(idDescarga);
        cruceDatosDTO.setProveedores(proveedores);
        cruceDatosDTO.setTodos(todos);
        ExportacionMP exportacionMP = exportacionMPService.getById(cruceDatosDTO.getIddescarga());
        if(exportacionMP==null){
            throw new Exception("No se encontraron Datos con el ID DE DESCARGA" + cruceDatosDTO.getIddescarga());
        }

        ByteArrayInputStream stream = null;
        try {
            stream = importacionService.descargarCruceDeDatos(cruceDatosDTO);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + exportacionMP.getDescripcion().replaceAll(".xlsx","") + "_" + exportacionMP.getFechacarga() + ".xlsx");
        ver("Termino la exportacion " + cruceDatosDTO.getIddescarga());
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));

    }


}