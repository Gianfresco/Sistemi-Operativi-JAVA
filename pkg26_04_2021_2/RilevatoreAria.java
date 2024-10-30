package pkg26_04_2021_2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class RilevatoreAria extends Thread {
    PipedOutputStream pos = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private static final int ATTESA = 12 * 1000;    // millisecondi

    public RilevatoreAria(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));
        Random rnd = new Random();
        String val = "";
        String time = "";

        while (isRunning.get()) {
            val = rnd.nextFloat() * 110 + "";
            time = System.currentTimeMillis() + "";
            try {
                bw.write(val);
                bw.newLine();
                bw.write(time);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(ATTESA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void fermaRilevatoreAria() {
        isRunning.set(false);
    }
}
