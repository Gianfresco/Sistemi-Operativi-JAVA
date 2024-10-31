package pkg18_06_2021_2;

import java.io.Serializable;

public class Rilevazione implements Serializable{
    private int valore;
    private long timestamp;

    public Rilevazione(int valore, long timestamp) {
        this.valore = valore;
        this.timestamp = timestamp;
    }

    public int getValore() {
        return valore;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
