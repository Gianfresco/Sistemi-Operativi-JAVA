package pkg14_07_2020_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class AvvisaConsumatori implements Runnable{
    PipedInputStream pis = new PipedInputStream();
    private AtomicBoolean isRunning = new AtomicBoolean(false);

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
            System.exit(-1);
        }

        while (isRunning.get()) {
            Warning wrn = null;

            try {
                wrn = (Warning)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(wrn.getMessaggio());

            if (wrn.getMessaggio().equals("fine")){
                termina();
            }
        }
    }

    private void termina() {
        isRunning.set(false);
    }
}
