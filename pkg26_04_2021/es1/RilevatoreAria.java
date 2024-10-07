package es1;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RilevatoreAria extends Thread {
    private PipedOutputStream pos = null;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public RilevatoreAria(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));

        while (isRunning.get()) {
            try {
                bw.write((int)(110 * Math.random()) + "");
                bw.newLine();
                bw.write((System.currentTimeMillis() / 1000) + "");
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void fermaRilevatore() {
        isRunning.set(false);
    }
}
