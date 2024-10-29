package esame8.stream;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaA implements Runnable {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedOutputStream pos = null;

    public MacchinaA(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            System.out.println("Inizio lavorazione prodotto...");
            
            try {
                Thread.sleep(200);
                System.out.println("Fine lavorazione prodotto");

                byte[] buffer = null;
                String pg = "prodotto-grezzo";

                try {
                    buffer = pg.getBytes("UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    pos.write(buffer);
                    pos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
