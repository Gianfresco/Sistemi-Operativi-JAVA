package pkg18_06_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();

        PipedOutputStream pos = null;
        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        int soglia = 0;

        while (true) {
            System.out.println("Inserire il valore di soglia... ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                soglia = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Errore di I/O!");
            } catch (NumberFormatException e) {
                System.out.println("Formato del numero non valido!");
                continue;
            }

            if (soglia < 100 || soglia > 200) {
                System.out.println("Il valore della soglia deve essere compreso tra 100 e 200");
            } else {
                break;
            }
        }

        GeneraRilevazioni gr = new GeneraRilevazioni(pos);
        gr.start();

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        int count = 0;
        int countCons = 0;
        Rilevazione rlv = null;

        while (count <= 10) {
            try {
                rlv = (Rilevazione) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-3);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(-4);
            }

            if (rlv.getValore() <= 50) {
                System.out.println("Rilevazione: " + rlv.getValore() + " - BASSO");
            } else if (rlv.getValore() > 50 || rlv.getValore() < 100) {
                System.out.println("Rilevazione: " + rlv.getValore() + " - MODERATO");
            } else if (rlv.getValore() >= 100) {
                System.out.println("Rilevazione: " + rlv.getValore() + " - ALTO");
            }

            if (rlv.getValore() > soglia) {
                countCons++;
            } else {
                countCons = 0;
            }

            if (countCons >= 3) {
                System.out.println("Attenzione!");
            }

            count++;
        }

        gr.termina();
        try {
            gr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
