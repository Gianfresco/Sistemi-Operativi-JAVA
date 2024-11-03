package esercizio10;

public class Macchina {
    private int id = 0;
    private int pezziProd = 0;

    public synchronized void setMacchinaID(int id) {
        this.id = id;
    } 

    public synchronized void setPezziProdotti(int pezzi) {
        this.pezziProd = pezzi;
    }

    public synchronized int getMacchinaID() {
        return id;
    }

    public synchronized int getPezziProdotti() {
        return pezziProd;
    }
}
