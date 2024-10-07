package pkg09_09_2024.es2;

public class Storico {
    private int cambiRepentini = 0;

    public synchronized void addCambiRepentini() {
        this.cambiRepentini++;
    }

    public synchronized int getCambiRepentini() {
        return this.cambiRepentini;
    }
}
