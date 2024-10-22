package esercizio5.buffered;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.Random;

public class Sensor extends Thread {
    private PipedOutputStream pos = null;
    
    public Sensor(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        final Random rnd = new Random();
        DataOutputStream dos = new DataOutputStream(pos);

        while (true) {
            try {
                float temp = 18 + rnd.nextFloat() * (21 - 18);
                try {
                    dos.writeFloat(temp);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
