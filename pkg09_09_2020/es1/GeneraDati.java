package pkg09_09_2020.es1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GeneraDati implements Runnable{
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedOutputStream pos = null;

    public GeneraDati (PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        Random random = new Random();
        isRunning.set(true);

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-30);
        }

        while (isRunning.get()) {
            float tmp = random.nextFloat() * 25 - 5;    // float casuale da -5 a 25
            int umd = random.nextInt(70) + 15;

            Misure misure = new Misure(tmp, umd);
            System.out.println("GeneraDati--- Temp: " + tmp + " Umidità: " + umd);

            try {
                oos.writeObject(misure);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {}
        }
    }

    public void finisci() {
        isRunning.set(false);
    }

}
