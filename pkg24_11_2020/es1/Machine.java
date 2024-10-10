package pkg24_11_2020.es1;

import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Machine extends Thread {
    private PipedOutputStream pos = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private int prodottiCreati = 0;

    public Machine(PipedOutputStream pos) {
        this.pos = pos;
    }

    @SuppressWarnings("static-access")
    public void run() {
        isRunning.set(true);
        Random rnd = new Random();

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (isRunning.get()) {
            float num = -1000 + rnd.nextFloat() * 2000;
            prodottiCreati++;
            Message m = new Message(prodottiCreati, num);

            try {
                oos.writeObject(m);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
