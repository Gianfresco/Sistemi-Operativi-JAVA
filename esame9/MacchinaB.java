package esame9;

import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaB implements Runnable {
    private ControlloProduzione cp = null;
    final AtomicBoolean isRunning = new AtomicBoolean(false);

    public MacchinaB(ControlloProduzione cp) {
        this.cp = cp;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            System.out.println("Inizio lavorazione prodotto finito...");

            try {
                int sleeptime = (int)(100 + Math.random() * 50);
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Termine produzione prodotto finito.");
            cp.addProdottoFinito();
        }
    }

    public void stopMacchinaB() {
        isRunning.set(false);
    }
}
