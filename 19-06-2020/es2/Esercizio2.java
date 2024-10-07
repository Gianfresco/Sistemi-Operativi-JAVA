package es2;

import java.io.*;
import java.nio.channels.Pipe;

public class Esercizio2 {
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
        Thread tiu = new Thread(iu);
        tiu.start();

        VerificaInput vi = new VerificaInput();

        ScansionaInput si = new ScansionaInput(pis, vi);
        Thread tsi = new Thread(si);
        tsi.start();

        while (true) {
            
        }
    }
}
