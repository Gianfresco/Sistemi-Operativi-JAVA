package pkg26_04_2021_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = null;
        try {
            pis = new PipedInputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(pis));
        RilevatoreAria ra = new RilevatoreAria(pos);
        ra.start();

        float val = 0.0F;
        long currTime = 0;
        long oldTime = 0;

        for (int i = 0; i < 8; i++) {
            try {
                val = Float.parseFloat(br.readLine());
                currTime = Long.parseLong(br.readLine());

                if (currTime > (oldTime + 15000) && oldTime != 0) {
                    System.out.println("Attenzione!");
                }

                if (val < 20) {
                    System.out.println("Basso:\t" + val);
                } else if (val >= 20 || val <= 75) {
                    System.out.println("Medio:\t" + val);
                } else {
                    System.out.println("Alto:\t" + val);
                }

                oldTime = currTime;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ra.fermaRilevatoreAria();
    }
}
