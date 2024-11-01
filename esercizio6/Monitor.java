package esercizio6;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Monitor implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private ThreadLoad tl = null;

    public Monitor(ThreadLoad tl) {
        this.tl = tl;
    }
    
    public void run() {
        isRunning.set(true);
        Random rnd = new Random();

        while (isRunning.get()) {
            tl.setId(rnd.nextInt(10));
            tl.setLoad(rnd.nextDouble() * 100);
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
