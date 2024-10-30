package pkg24_11_2020_2;

import java.io.Serializable;

public class Message implements Serializable{
    float qlt = 0.0F;

    public synchronized void writeQuality(float quality) {
        this.qlt = quality;
    }

    public synchronized float readQuality() {
        return qlt;
    }
}
