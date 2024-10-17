package esame10;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulaProduzione extends Thread {
    private Macchina mcn = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public SimulaProduzione(Macchina mcn) {
        this.mcn = mcn;
    }

    public void run() {
        Random rnd = new Random();
        int pezziProdotti = mcn.getPezziProdotti();

        isRunning.set(true);

        while (isRunning.get()) {
            int pezziProdNow = rnd.nextInt(10);
            pezziProdotti += pezziProdNow;
            mcn.setPezziProdotti(pezziProdotti);

            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {

            }
        }
    }

    public void termina() {
        isRunning.set(false);
        Thread.currentThread().interrupt();
    }
}
