package com.agencia.pf.controllers;

import com.agencia.pf.models.ResponseMessage;
import com.agencia.pf.services.ImportacionCarteraService;
import com.agencia.pf.utiles.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static com.agencia.pf.utiles.Utiles.showlogInfo;

@CrossOrigin
@RestController
@RequestMapping("/importacionCartera")
public class ImportacionCarteraController {

    @Autowired
    ImportacionCarteraService importacionCarteraService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestHeader("IdProveedor") Long idproveedor) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                ArrayList<String> errores = new ArrayList<String>();
                showlogInfo(this,"Importando archivo: " + file.getOriginalFilename());
                // System.out.println("Importando archivo: " + file.getOriginalFilename());
                importacionCarteraService.save2(file,errores,idproveedor);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, errores, idproveedor));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println("ERROR : " + e.getMessage());
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
