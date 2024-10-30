package pkg09_09_2020_2;

import java.util.concurrent.atomic.AtomicBoolean;

public class Avvisi extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    Storico stc = null;

    public Avvisi(Storico storico) {
        this.stc = storico;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (stc.getCambiRepentini() != 0) {
                System.out.println("Attenzione! - " + stc.getCambiRepentini());
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
