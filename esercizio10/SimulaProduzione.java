package esercizio10;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulaProduzione extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    Random rnd = new Random();
    Macchina mcn = null;

    public SimulaProduzione(Macchina macchina) {
        this.mcn = macchina;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mcn.setPezziProdotti(mcn.getPezziProdotti() + rnd.nextInt(10));
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
