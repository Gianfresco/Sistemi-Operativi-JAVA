package pkg19_06_2020.es1;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScansionaInput implements Runnable {
    final private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedInputStream pis = null;

    public ScansionaInput(PipedInputStream pis) {
        this.pis = pis;
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

            System.out.println("InputUtente ha inviato: " + line);

            if (line.equals("abcde") || line.equals("1234")) {
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
