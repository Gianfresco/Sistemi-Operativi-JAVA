package pkg19_06_2020_2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;
        VerificaInput vi = new VerificaInput();

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        InputUtente iu = new InputUtente(pos);
        Thread Tiu = new Thread(iu);
        Tiu.start();

        ScansionaInput si = new ScansionaInput(pis, vi);
        Thread Tsi = new Thread(si);
        Tsi.start();

        while (true) {
            try {
                Thread.sleep(200);
                if (vi.getNumStringheSospette() > 2) {
                    iu.termina();
                    si.termina();
                    System.out.println("Numero stringhe sospette troppo elevato!");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Tiu.join();
            Tsi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
