package esercizio6;

import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sorter implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    ThreadLoad tl = null;
    PipedOutputStream pos = null;

    public Sorter(PipedOutputStream pos, ThreadLoad tl) {
        this.tl = tl;
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
    }
}
