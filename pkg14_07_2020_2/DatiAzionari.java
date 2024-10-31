package pkg14_07_2020_2;

public class DatiAzionari {
    private int indice;
    private int valore;

    public synchronized void setIndice(int indice) {
        this.indice = indice;
    }

    public synchronized void setValore(int valore) {
        this.valore = valore;
    }

    public synchronized int getIndice() {
        return indice;
    }

    public synchronized int getValore() {
        return valore;
    }
}
