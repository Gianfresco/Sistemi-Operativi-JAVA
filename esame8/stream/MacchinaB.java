package esame8.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaB implements Runnable {
    private PipedInputStream pis = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private int lavoratiFiniti = 0;

    public MacchinaB(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run() {
        isRunning.set(true);
        
        while (isRunning.get()) {
            byte[] buffer = new byte[128];
            int nread = 0;

            try {
                nread = pis.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String received = new String(buffer, 0, nread, Charset.forName("UTF-8"));
            System.out.println("Arrivato prodotto grezzo " + received);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lavoratiFiniti++;
            System.out.println("Fine lavorazione, totale prodotti: " + lavoratiFiniti);
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
