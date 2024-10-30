package pkg09_07_2021_2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulaConsumi implements Runnable{
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    Consumi cons = new Consumi();
    Random rnd = new Random();

    public SimulaConsumi(Consumi cons) {
        this.cons = cons;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            cons.setConsumi(30 * rnd.nextFloat());

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
