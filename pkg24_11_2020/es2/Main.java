package pkg24_11_2020.es2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;
        Overall ovr = new Overall();

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Quality qlt = new Quality(pis, ovr);
        qlt.start();

        Machine mcn = new Machine(pos);
        mcn.start();

        while (true) {
            try {
                if (ovr.getDifetti() > ovr.getCorretti()) {
                    mcn.termina();
                    qlt.termina();
                    System.out.println("Troppi oggetti difettosi!");
                    break;
                }

                System.out.println("Corretti: " + ovr.getCorretti() + ", Difettosi: " + ovr.getDifetti());
                
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            mcn.join();
            qlt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
