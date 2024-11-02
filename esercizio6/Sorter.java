package esercizio6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
        int id = 0;
        double maxLoad = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));

        while (isRunning.get()) {
            if (tl.getLoad() > maxLoad) {
                maxLoad = tl.getLoad();
                id = tl.getId();
            }

            String result = "ID: " + id + " LOAD: " + maxLoad;
            try {
                bw.write(result);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
   }
}
