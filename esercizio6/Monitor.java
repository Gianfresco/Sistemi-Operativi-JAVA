package esercizio6;

import java.util.concurrent.atomic.AtomicBoolean;

public class Monitor implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private ThreadLoad tl = null;

    public Monitor(ThreadLoad tl) {
        this.tl = tl;
    }
    
    public void run() {
        isRunning.set(true);
    }
}
