package pkg09_09_2020_2;

public class Storico {
    private int cambiRepentini;

    public synchronized void addCambioRepentino() {
        cambiRepentini++;
    }

    public synchronized int getCambiRepentini() {
        return cambiRepentini;
    }
}
