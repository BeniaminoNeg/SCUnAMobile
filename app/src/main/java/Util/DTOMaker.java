package Util;

import org.dto.DTO;

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

    public DTO getPayments(String idUtente,int Amount){
        DTO dto = new DTO();
        dto.setFunzione("Payment");
        dto.aggiungiOggettoTrasferimento(idUtente);
        dto.aggiungiOggettoTrasferimento(Amount);
        return dto;
    }

    public DTO getAutenticazioneStanza(String idUtente,String idStanza){

        DTO dto = new DTO();
        dto.setFunzione("AutenticazioneStanza");
        dto.aggiungiOggettoTrasferimento(idUtente);
        dto.aggiungiOggettoTrasferimento(idStanza);
        return dto;
    }

    public DTO getRispostaAutenticazioneStanza(boolean autorizzazione){
        DTO dto = new DTO();
        dto.setFunzione("RispostaAutenticazioneStanza");
        dto.aggiungiOggettoTrasferimento(autorizzazione);
        return dto;
    }


}
