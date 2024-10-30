package pkg09_09_2020_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;
        Storico stc = new Storico();
        Misure msr = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        GeneraDati gd = new GeneraDati(pos);
        Thread gdT = new Thread(gd);
        gdT.start();

        Avvisi avs = new Avvisi(stc);
        avs.start();

        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        int umdPrec = 0;

        while (true) {
            try {
                msr = (Misure)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (umdPrec != 0 && msr.getUmidita() > (umdPrec + umdPrec / 100 * 20)) {
                stc.addCambioRepentino();
            }

            if (msr.getUmidita() > (umdPrec + umdPrec / 100 * 40)) {
                gd.termina();
                avs.termina();
                break;
            }
        }

        try {
            gdT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
