package pkg26_04_2021;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class RilevatoreAria extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(true);
    private PipedOutputStream pos = null;

    public RilevatoreAria(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));

        while (isRunning.get()) {
            try {
                bw.write((int)(110.0 * Math.random()) + "");
                bw.newLine();
                bw.write((int)(System.currentTimeMillis()) + "");
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(MAX_PRIORITY);
            }

            try {
                Thread.sleep(12 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
