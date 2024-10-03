package pkg19_06_2020.es2;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class InputUtente implements Runnable {
    final private AtomicBoolean isRunning = new AtomicBoolean(false);
    private PipedOutputStream pos = null;

    public InputUtente(final PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        while (isRunning.get()) {
            System.out.println("Inserire una stringa... ");

            try {
                String line = stdin.readLine();
                bw.write(line);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning.set(false);
    }
}
