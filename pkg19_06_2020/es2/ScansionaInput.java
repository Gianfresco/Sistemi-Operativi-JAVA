package pkg19_06_2020.es2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

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
                vi.addStrigheSospette();
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
