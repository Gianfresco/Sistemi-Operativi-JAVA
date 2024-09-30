package es1;

import java.io.*;
//import java.time.Year;

public class Esercizio1 {
    public static void main(String args[]) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(-1);
        }

        GeneraDati gd = new GeneraDati(pos);
        Thread tgd = new Thread(gd);
        tgd.start();

        ObjectInputStream dis = null;

        try {
            dis = new ObjectInputStream(pis);
        } catch (IOException e2) {
            e2.printStackTrace();
            System.exit(-2);
        }

        // controllo umidità
        boolean first = true;
        int umiditaPrecedente = 0;

        while (true) {
            Misure mis = null;

            try {
                mis = (Misure)dis.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e);    
            }

            int umidita = mis.getUmidita();

            if (!first) {
                float variazione = (float) Math.abs(umidita - umiditaPrecedente) / umiditaPrecedente;
                System.out.println("Variazione umidità registrata: " + variazione);
                
                if (variazione > 0.2) {
                    System.out.println("Variazione superiore al 20%! -> TERMINAZIONE");
                    gd.finisci();
                    break;
                }

            } else {
                first = false;
            }

            umiditaPrecedente = umidita;

        }
    }
}