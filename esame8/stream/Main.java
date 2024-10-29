
package esame8.stream;

import java.io.IOException;
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

        MacchinaA mA = new MacchinaA(pos);
        Thread TmA = new Thread(mA);
        TmA.start();

        MacchinaB mB = new MacchinaB(pis);
        Thread TmB = new Thread(mB);
        TmB.start();

        try {
            Thread.sleep(60 * 1000);
            mA.termina();
            mB.termina();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TmA.join();
            TmB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
