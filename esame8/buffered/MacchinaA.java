package esame8.buffered;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaA implements Runnable {
    private PipedOutputStream pos = null;
    final AtomicBoolean isRunning = new AtomicBoolean(false);

    public MacchinaA(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));

        while (isRunning.get()) {
            System.out.println("Inizio lavorazione...");

            try {
                Thread.sleep(200);
                System.out.println("Fine lavorazione prodotto.");

                String pg = "prodotto-grezzo";
                try {
                    bw.write(pg);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
