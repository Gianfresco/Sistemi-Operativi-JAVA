package pkg24_11_2020.es1;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Quality extends Thread{
    private PipedInputStream pis = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public Quality(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run() {
        isRunning.set(true);

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (isRunning.get()) {
            try {
                Message m = (Message)ois.readObject();
                float fIndex = m.getFIndex();

                if (fIndex > 0) {
                    System.out.println("Pezzo " + m.getPezziProdotti() + " --- OK");
                } else {
                    System.out.println("Pezzo " + m.getPezziProdotti() + " --- ERRORE");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
