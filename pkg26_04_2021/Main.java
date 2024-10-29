package pkg26_04_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();

        try {
            PipedInputStream pis = new PipedInputStream(pos);
            BufferedReader br = new BufferedReader(new InputStreamReader(pis));

            RilevatoreAria ra = new RilevatoreAria(pos);
            ra.start();

            long oldTime = 0L;
            
            for (int i = 0; i < 8; i++) {
                int val = Integer.parseInt(br.readLine());
                long time = Long.parseLong(br.readLine());
                
                if (time > (oldTime + 15 * 1000)) {
                    System.out.println("Attenzione");
                }

                if (val < 20) {
                    System.out.println("Basso\t" + val);
                } else if (val >= 20 || val <= 75) {
                    System.out.println("Medio\t" + val);
                } else {
                    System.out.println("Alto\t" + val);
                }
            }

            try {
                br.close();
                ra.termina();
                ra.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
