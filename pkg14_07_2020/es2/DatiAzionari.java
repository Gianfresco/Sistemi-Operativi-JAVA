package pkg14_07_2020.es2;

public class DatiAzionari {
    private String indice = null;
    private int valore = 0;

    public synchronized void setIndice(String indice) {
        this.indice = indice;
    }
    
    public synchronized void setValore(int valore) {
        this.valore = valore;
    }

    public synchronized String getIndice() {
        return indice;
    }

    public synchronized int getValore() {
        return valore;
    }
}
