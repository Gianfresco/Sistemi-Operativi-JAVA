package pkg24_11_2020.es2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Machine extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedOutputStream pos = null;
    public int prodottiCreati = 0;

    public Machine(PipedOutputStream pos){
        this.pos = pos;
    }
    
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

            Message msg = new Message(num, prodottiCreati);

            try {
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
