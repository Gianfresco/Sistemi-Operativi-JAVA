package esercizio5.stream;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.Random;

public class Sensor extends Thread {
    PipedOutputStream pos = null;

    public Sensor(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        final Random rnd = new Random();

        while (true) {
            try {
                float temp = 18 + rnd.nextFloat() * (21 - 18);
                String strTemp = temp + "";
                byte[] msg = strTemp.getBytes();

                try {
                    pos.write(msg);
                    pos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }    
        }
    }
}
