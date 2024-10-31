package pkg24_11_2020_2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Machine extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedOutputStream pos = new PipedOutputStream();
    Random rnd = new Random();

    public Machine(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        Message msg = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (isRunning.get()) {
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            float qual = 2000 * rnd.nextFloat() - 1000;

            msg = new Message();
            msg.writeQuality(qual);

            try {
                oos.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
