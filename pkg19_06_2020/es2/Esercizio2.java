package pkg19_06_2020.es2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Esercizio2 {
    @SuppressWarnings("static-access")
    public static void main(String args[]) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        InputUtente iu = new InputUtente(pos);
        Thread tiu = new Thread(iu);
        tiu.start();
        
        VerificaInput vi = new VerificaInput();
        
        ScansionaInput si = new ScansionaInput(pis, vi);
        Thread tsi = new Thread(si);
        tsi.start();

        while (true) {
            try {
                Thread.currentThread().sleep(200);
                if (vi.getStringheSospette() > 3) {
                    System.out.println("Rilevate più di 3 stringhe sospette -> TERMINAZIONE");
                    iu.stop();
                    si.stop();
                    tiu.join();
                    tsi.join();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
