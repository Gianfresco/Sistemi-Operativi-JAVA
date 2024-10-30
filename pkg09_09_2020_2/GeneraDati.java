package pkg09_09_2020_2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GeneraDati implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedOutputStream pos = null;

    public GeneraDati(PipedOutputStream pos) {
        this.pos = pos;
    }
    
    public void run() {
        isRunning.set(true);
        Misure msr = null;
        Random rnd = new Random();

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (isRunning.get()) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            float temp = 25 * rnd.nextFloat() - 5;
            int umd = 85 * rnd.nextInt() + 15;

            msr = new Misure(temp, umd);
            try {
                oos.writeObject(msr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
