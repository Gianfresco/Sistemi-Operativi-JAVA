package es2;

import java.io.*;

public class Esercizio2 {
    public static void main(String args[]) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        GeneraDati gd = new GeneraDati(pos);
        Thread tgd = new Thread(gd);
        tgd.start();

        Storico storico = new Storico();

        Avvisi avvisi = new Avvisi(storico);
        avvisi.start();

        ObjectInputStream dis = null;

        try {
            dis = new ObjectInputStream(pis);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-2);
        }

        // controllo umidità
        boolean first = true;
        int umiditaPrecedente = 0;

        while (true) {
            Misure mis = null;

            try {
                mis = (Misure) dis.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e);
                System.exit(-3);
            }

            int umidita = mis.getUmidita();

            if (!first) {
                float variazione = (float) Math.abs(umidita - umiditaPrecedente) / umiditaPrecedente;
                System.out.println("Variazione umidità registrata: " + variazione);

                // aggiornamento dello storico
                if (variazione > 0.2) {
                    System.out.println("Aggiornamento storico cambi repentini...");
                    storico.addCambiRepentini();
                }

                // verifica condizione di terminazione
                if (variazione > 0.4) {
                    System.out.println("Variazione superiore al 40% -> TERMINAZIONE");
                    gd.finisci();
                    avvisi.finisci();
                    break;
                } else {
                    first = false;
                }

                umiditaPrecedente = umidita;
            }
        }
    }
}
