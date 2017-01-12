package Util;

/**
 * Created by beniamino on 12/01/17.
 */
public class DatiServer {
    private static DatiServer ourInstance = new DatiServer();

    private String IndirizzoIP;
    private Integer Porta;

    public String getIndirizzoIP() {
        return IndirizzoIP;
    }

    public void setIndirizzoIP(String indirizzoIP) {
        IndirizzoIP = indirizzoIP;
    }

    public Integer getPorta() {
        return Porta;
    }

    public void setPorta(Integer porta) {
        Porta = porta;
    }

    public static DatiServer getInstance() {
        return ourInstance;
    }

    private DatiServer() {
    }
}
