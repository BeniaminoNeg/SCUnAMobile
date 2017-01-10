package com.example.beniamino.scunamobile.Util;



/**
 * Created by gioele on 04/03/16.
 */
public class DTOMaker {

    private static DTOMaker singletonInstance = null;


    private DTOMaker() {


    }

    public static DTOMaker getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DTOMaker();
        }
        return singletonInstance;
    }


    public DTO getPingDTO() {
        DTO dto = new DTO();
        dto.setFunzione("Ping");
        return dto;
    }


    public DTO getAutenticazione(String id, String password) {
        DTO dto = new DTO();
        dto.setFunzione("Autenticazione");
        dto.aggiungiOggettoTrasferimento(id);
        dto.aggiungiOggettoTrasferimento(password);
        return dto;
    }



}
