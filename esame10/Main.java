package esame10;

import java.io.*;

public class Main {
    @SuppressWarnings({ "static-access", "unused" })
    public static void main(String[] args) {
        Macchina mcn = new Macchina();
        SimulaProduzione sp = new SimulaProduzione(mcn);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserire ID della macchina... ");
        String mcnID = null;
        try {
            mcnID = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Inserire obiettivo produzione... ");
        String obj = null;
        try {
            obj = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mcn.setMacchinaID(Integer.parseInt(mcnID));
        String pezziProd = null;
        mcn.setPezziProdotti(0);

        sp.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.currentThread().sleep(10 * 1000);
                System.out.println("Pezzi prodotti dalla macchina: " + mcn.getPezziProdotti());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (mcn.getPezziProdotti() >= Integer.parseInt(obj)) {
                System.out.println("Obiettivo raggiunto");
            } else {
                System.out.println("Obiettivo non raggiunto");
            }
        }

        sp.termina();
        try {
            sp.join();
        } catch (Exception e) {
            
        }
    }
}
