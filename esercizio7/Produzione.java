package esercizio7;

public class Produzione {
    private int prodotti = 0;

    public synchronized void incremento() {
        if (Math.random() < 0.25) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        prodotti++;
    }

    public synchronized int stampaProduzione() {
        return prodotti;
    }
}
