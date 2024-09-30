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
                int val
            }
        } catch (Exception e) {
            
        }
    }
}
