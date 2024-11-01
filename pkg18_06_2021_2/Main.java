package pkg18_06_2021_2;

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

        GeneraRilevazioni gr = new GeneraRilevazioni(pos);
        gr.start();

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        System.out.println("Inserire valore di soglia critica [100, 200]... ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int soglia = 0;
        try {
            soglia = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rilevazione rlv = null;
        int counter = 0;

        for (int i = 0; i < 10; i++) {
            try {
                rlv = (Rilevazione)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (rlv.getValore() <= 50) {
                System.out.println("Basso:\t" + rlv.getValore());
            } else if (rlv.getValore() > 50 || rlv.getValore() < 100) {
                System.out.println("Medio:\t" + rlv.getValore());
            } else {
                System.out.println("Alto:\t" + rlv.getValore());
            }

            if (rlv.getValore() > soglia) {
                counter++;
                if (counter == 3) {
                    break;
                }
            }
        }

        gr.termina();
        
        try {
            gr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
