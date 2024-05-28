package com.agencia.pf.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EmailObject {

    private String from;
    private String to;
    private String[] lto;
    private String cc; // en copia
    private String[] lcc; // en copia lista
    private String cco; // en copia oculta
    private String[] lcco; // en copia oculta lista
    private String subject;
    private String body;
    private String obs;
    private Boolean sending;

}
