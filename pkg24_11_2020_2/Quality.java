package pkg24_11_2020_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Quality extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedInputStream pis = new PipedInputStream();
    Overall ovr = new Overall();

    public Quality(PipedInputStream pis, Overall ovr) {
        this.pis = pis;
        this.ovr = ovr;
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
                Message msg = (Message) ois.readObject();
                float qual = msg.readQuality();

                if (qual < 0) {
                    System.out.println("Errore, qualità: " + qual + " - PEZZO DIFETTOSO");
                    ovr.addDifettosi();
                } else {
                    System.out.println("OK, qualità:" + qual);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
