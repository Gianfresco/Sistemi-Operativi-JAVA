package pkg09_07_2021;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimulaConsumi implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private Consumi cns = null;

    private SimulaConsumi(Consumi cns) {
        this.cns = cns;
    }

    public void run() {
        
    }
}
