package esame9;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.Charset;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ControlloProduzione ctrlProd = new ControlloProduzione();

        MacchinaA mA1 = new MacchinaA(ctrlProd);
        Thread TmA1 = new Thread(mA1);
        MacchinaA mA2 = new MacchinaA(ctrlProd);
        Thread TmA2 = new Thread(mA2);
        TmA1.start();
        TmA2.start();

        MacchinaB mB1 = new MacchinaB(ctrlProd);
        Thread TmB1 = new Thread();
        TmB1.start();

        for (int i = 0; i < 15; i++) {
            byte[] buffer = new byte[128];
            try {
                int nread = pis.read(buffer);
                String msg = new String(buffer, 0, nread, Charset.forName("UTF-8"));
                System.out.println("MAIN --- arrivato prodotto finito");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mA1.stopMacchinaA();
        mA2.stopMacchinaA();
        mB1.stopMacchinaB();

        try {
            TmA1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TmA2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TmB1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Semilavorati: " + ctrlProd.getSemilavorati());
        System.out.println("Prodotti finiti: " + ctrlProd.getProdottiFiniti());
    }
}
