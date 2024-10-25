package pkg09_07_2021;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimulaConsumi implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private Consumi cns = null;

    public SimulaConsumi(Consumi cns) {
        this.cns = cns;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            cns.setConsumi((float) (Math.random() * 30));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
