package esame8.object;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaA implements Runnable {
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    public PipedOutputStream pos = null;

    public MacchinaA(PipedOutputStream pos) {
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
            System.out.println("Inizio lavorazione prodotto... ");

            try {
                Thread.sleep(200);
                System.out.println("Fine lavorazione.");

                Message m = new Message("prodotto-grezzo");
                try {
                    oos.writeObject(m);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-2);
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
