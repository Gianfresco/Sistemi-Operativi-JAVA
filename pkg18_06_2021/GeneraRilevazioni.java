package pkg18_06_2021;

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
            Rilevazione rlv = new Rilevazione((int) (200 * Math.random()), System.currentTimeMillis());

            try {
                oos.writeObject(rlv);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-2);
            }

            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void termina() {
        isRunning.set(false);
    }
}
