package es1;

import java.io.*;

public class Esercizio1 {
    public static void main(String args[]) {
        PipedOutputStream pos = new PipedOutputStream();

        try {
            PipedInputStream pis = new PipedInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(pis));
            RilevatoreAria ra = new RilevatoreAria(pos);
            ra.start();
            long oldTime = 0;

            for (int i = 0; i < 8; ++i) {
                int val = Integer.parseInt(br.readLine());
                long newTime = Long.parseLong(br.readLine());

                if (val < 20) {
                    System.out.println(val + " - basso");
                } else if (val < 75) {
                    System.out.println(val + " - medio");
                } else {
                    System.out.println(val + " - alto");
                }

                long elapsedTime = newTime + oldTime;
                if (elapsedTime > 15 && oldTime != 0) {
                    System.out.println("ATTENZIONE: " + elapsedTime);
                    oldTime = newTime;
                }
            }
            
            ra.fermaRilevatore();

            try {
                ra.join();
                System.out.println("Il rilevatore è stato terminato.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Il processo principale è stato completato.");
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
