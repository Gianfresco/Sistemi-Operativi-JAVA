package esame10;

public class Macchina {
    private int macchinaID;
    private int pezziProdotti;

    public synchronized void setMacchinaID(int macchinaID) {
        this.macchinaID = macchinaID;
    }

    public synchronized void setPezziProdotti(int pezziProdotti) {
        this.pezziProdotti = pezziProdotti;
    }

    public synchronized int getMacchinaID() {
        return macchinaID;
    }

    public synchronized int getPezziProdotti() {
        return pezziProdotti;
    }
}