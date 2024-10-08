package pkg14_07_2020.es2;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AvvisaConsumatori implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedInputStream pis = null;

    public AvvisaConsumatori(PipedInputStream pis) {
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
            Warning wrn = null;

            try {
                wrn = (Warning) ois.readObject();
                if (wrn.getMessage().equals("fine")) {
                    isRunning.set(false);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            System.out.println("AvvisaConsumatori --- valore: " + wrn.getValore() + " " + wrn.getMessage());
        }
    }
}
