package pkg24_11_2020_2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        Overall ovr = new Overall();
        PipedInputStream pis = new PipedInputStream();
        
        PipedOutputStream pos = null;
        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Machine mcn = new Machine(pos);
        mcn.start();

        Quality qlt = new Quality(pis, ovr);
        qlt.start();

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mcn.termina();
        qlt.termina();

        try {
            mcn.join();
            qlt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFine produzione - Corretti: " + ovr.getCorretti() + ", Difettosi: " + ovr.getDifettosi());
        float eff = ovr.getCorretti() / (float)(ovr.getCorretti() + ovr.getDifettosi()) * 100;
        System.out.println("Efficienza produzione al " + eff + "%");
    }
}
