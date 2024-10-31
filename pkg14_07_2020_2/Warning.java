package pkg14_07_2020_2;

import java.io.Serializable;

public class Warning implements Serializable{
    private int valore = 0;
    private String messaggio = null;

    public Warning(int valore, String messaggio) {
        this.valore = valore;
        this.messaggio = messaggio;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public int getValore() {
        return valore;
    }
}
