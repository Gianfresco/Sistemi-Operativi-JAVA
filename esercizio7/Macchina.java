package esercizio7;

import java.util.concurrent.atomic.AtomicBoolean;

public class Macchina extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    Produzione prod = null;

    public Macchina(Produzione prod) {
        this.prod = prod;
    }

    public void run() {
        isRunning.set(true);
        int i = 0;

        while (isRunning.get() && i < 35) {
            try {
                int timer = (int)(Math.random()) * 100 + 500;
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            prod.incremento();
            
            i++;
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
