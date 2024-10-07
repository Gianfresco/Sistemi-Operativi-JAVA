package es2;

import java.io.*;
import java.util.concurrent.atomic.*;

public class ScansionaInput implements Runnable {
    final private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedInputStream pis = null;
    private VerificaInput vi = null;

    public ScansionaInput(PipedInputStream pis, VerificaInput vi) {
        this.pis = pis;
        this.vi = vi;
    }

    public void run() {
        isRunning.set(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(pis));

        while (isRunning.get()) {
            String line = null;

            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            if (line.equals("abcde") || line.equals("1234")) {
                vi.addStringheSospette();
                System.out.println("pericolo");
            } else {
                System.out.println("ok");
            }
        }
    }

    public void stop() {
        isRunning.set(false);
    }
}
