package pkg24_11_2020.es1;

import java.io.*;

public class Esercizio1 {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Machine mcn = new Machine(pos);
        Quality qlt = new Quality(pis);

        mcn.start();
        qlt.start();

        try {
            Thread.currentThread().sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mcn.termina();
        qlt.termina();

        try {
            mcn.join();
            qlt.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
