package pkg18_06_2021;

import java.io.Serializable;

public class Rilevazione implements Serializable {
    int valore = 0;
    long timestamp = 0L;

    public Rilevazione(int valore, long timestamp){
        this.timestamp = timestamp;
        this.valore = valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getValore() {
        return valore;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
