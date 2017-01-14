package Util;

/**
 * Created by Gioele on 14/01/2017.
 */

public class DatiCarta {


    private static DatiCarta singletonInstance = null;


    public static DatiCarta getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DatiCarta();
        }
        return singletonInstance;
    }

    private String idCarta;

    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }
}
