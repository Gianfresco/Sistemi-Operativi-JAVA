package esercizio6;

import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedInputStream pis = null;
    Sorter srt = null;
    Monitor mnt = null;

    public Manager(Sorter srt, Monitor mnt, PipedInputStream pis) {
        this.pis = pis;
        this.mnt = mnt;
        this.srt = srt;
    }

    
}
