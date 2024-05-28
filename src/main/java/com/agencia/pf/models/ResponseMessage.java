package com.agencia.pf.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseMessage {
    private String message;
    private ArrayList<String> errores = new ArrayList<String>();
    private Long iddescarga;


    public ResponseMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, ArrayList<String> errores, Long iddescarga) {
        this.message = message;
        this.errores = errores;
        this.iddescarga = iddescarga;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<String> errores) {
        this.errores = errores;
    }

    public Long getIddescarga() {
        return iddescarga;
    }

    public void setIddescarga(Long iddescarga) {
        this.iddescarga = iddescarga;
    }
}

