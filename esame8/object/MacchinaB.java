package esame8.object;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaB implements Runnable {
    final private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedInputStream pis = null;
    private int lavoratiFiniti = 0;

    public MacchinaB(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run() {
        isRunning.set(true);
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isRunning.get()) {
            Message msg = null;
            try {
                msg = (Message)ois.readObject();
                System.out.println("Arrivato prodotto grezzo " + msg.getMessage());

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lavoratiFiniti++;
                System.out.println("Fine lavorazione, totale prodotti: " + lavoratiFiniti);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
