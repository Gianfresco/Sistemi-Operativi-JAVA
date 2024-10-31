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

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        GeneraRilevazioni gr = new GeneraRilevazioni(pos);
        gr.start();

        System.out.println("Inserire valore di soglia critica [100, 200]");
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

            int dato = rlv.getValore();

            if (dato <= 50) {
                System.out.println("Basso:\t" + dato);
            } else if (dato > 50 || dato < 100) {
                System.out.println("Medio:\t" + dato);
            } else {
                System.out.println("Alto:\t" + dato);
            }

            if (dato > soglia) {
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
