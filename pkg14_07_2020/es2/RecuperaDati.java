package pkg14_07_2020.es2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RecuperaDati extends Thread {
    private DatiAzionari da = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public RecuperaDati(DatiAzionari da) {
        this.da = da;
    }

    public void run() {
        Random rnd = new Random();
        isRunning.set(true);
        String indice = da.getIndice();

        while (isRunning.get()) {
            String vIndice = da.getIndice();

            if (!indice.equals(vIndice)) {
                System.out.println("Warning --- cambaito indice da monitorare in: " + vIndice);
                indice = vIndice;
            }

            int valore = 20 + rnd.nextInt(60);
            da.setValore(valore);

            System.out.println("RecuperaDati --- " + indice + ": " + valore);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {}
        }
    }

    public void terminaThread() {
        isRunning.set(false);
        Thread.currentThread().interrupt();
    }
}
