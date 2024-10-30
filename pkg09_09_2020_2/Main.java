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
        boolean first = true;

        while (true) {
            try {
                msr = (Misure)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (!first) {
                float variazione = (float) Math.abs(msr.getUmidita() - umdPrec) / umdPrec;

                System.out.println("Variazione umidità registrata: " + variazione + "%");
                
                if (variazione > 0.4) {
                    System.out.println("Rilevata variazione superiore al 40%, termino");
                    gd.termina();
                    avs.termina();
                    break;
                }

                if (variazione > 0.2) {
                    System.out.println("Rilevata variazione superiore al 20%");
                    stc.addCambioRepentino();
                }
            } else {
                first = false;
            }

            umdPrec = msr.getUmidita();
        }

        try {
            gdT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
