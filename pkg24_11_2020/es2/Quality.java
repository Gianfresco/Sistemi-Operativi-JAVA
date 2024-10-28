package pkg24_11_2020.es2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Quality extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedInputStream pis = null;
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
                Message msg = (Message)ois.readObject();

                if (msg.quality > 0) {
                    System.out.println("OK");
                    ovr.addCoretti();
                } else {
                    System.out.println("Errore");
                    ovr.addDifetti();
                }
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
