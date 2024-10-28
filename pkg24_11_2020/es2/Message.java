package pkg24_11_2020.es2;

import java.io.Serializable;

public class Message implements Serializable{
    public float quality = 0.0F;
    public int pezziProd = 0;

    public Message(float quality, int pezziProd) {
        this.quality = quality;
        this.pezziProd = pezziProd;
    }

    public float getQuality() {
        return quality;
    }

    public int getPezziProd() {
        return pezziProd;
    }
}
