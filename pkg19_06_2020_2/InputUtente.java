package pkg19_06_2020_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class InputUtente implements Runnable{
    private AtomicBoolean isRunning = new AtomicBoolean();
    PipedOutputStream pos = new PipedOutputStream();

    public InputUtente(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        isRunning.set(true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(pos));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        while (isRunning.get()) {
            System.out.println("Inserire una stringa... ");

            try {
                String str = stdin.readLine();
                bw.write(str);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
