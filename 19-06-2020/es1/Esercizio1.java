package es1;

import java.io.*;

public class Esercizio1 {
    public static void main(String args[]) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;

        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        InputUtente iu = new InputUtente(pos);
        Thread tui = new Thread(iu);
        tui.start();

        ScansionaInput si = new ScansionaInput(pis);
        Thread tsi = new Thread(si);
        tsi.start();

        try {
            tui.join();
            tsi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }
}
