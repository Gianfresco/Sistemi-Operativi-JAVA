package esercizio10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Macchina mcn = new Macchina();
        
        int prodObj = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Inserire ID macchina... ");
            mcn.setMacchinaID(Integer.parseInt(br.readLine()));

            System.out.println("Inserire obiettivo produzione");
            prodObj = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mcn.setPezziProdotti(0);

        SimulaProduzione sp = new SimulaProduzione(mcn);
        sp.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Controllo " + (i+1) + ": prodotti " + mcn.getPezziProdotti() + " pezzi");
            if (mcn.getPezziProdotti() >= prodObj) {
                System.out.println("Obiettivo di produzione raggiunto");
                break;
            }
        }

        sp.termina();
        try {
            sp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
