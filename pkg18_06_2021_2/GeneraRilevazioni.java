package pkg18_06_2021_2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class GeneraRilevazioni extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedOutputStream pos = null;

    public GeneraRilevazioni(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (isRunning.get()) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int val = (int)(Math.random() * 200);
            long time = System.currentTimeMillis();

            Rilevazione rlv = new Rilevazione(val, time);
            try {
                oos.writeObject(rlv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
