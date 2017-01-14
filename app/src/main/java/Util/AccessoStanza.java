package Util;

/**
 * Created by Gioele on 14/01/2017.
 */

public class AccessoStanza {

    private String idStanza;

    private static AccessoStanza singletonInstance = null;


    public static AccessoStanza getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AccessoStanza();
        }
        return singletonInstance;
    }

    public String getIdStanza() {
        return idStanza;
    }

    public void setIdStanza(String idStanza) {
        this.idStanza = idStanza;
    }
}
