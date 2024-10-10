package pkg24_11_2020.es1;

import java.io.Serializable;

public class Message implements Serializable {
    private int pezziProdotti = 0;
    private float fIndex = 0F;

    public Message(int pezziProdotti, float fIndex) {
        this.pezziProdotti = pezziProdotti;
        this.fIndex = fIndex;
    }

    public int getPezziProdotti() {
        return this.pezziProdotti;
    }

    public float getFIndex() {
        return this.fIndex;
    }
}
