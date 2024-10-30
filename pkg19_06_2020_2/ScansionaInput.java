package pkg19_06_2020_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScansionaInput implements Runnable{
    private AtomicBoolean isRunning = new AtomicBoolean();
    PipedInputStream pis = new PipedInputStream();
    private VerificaInput vi = null;

    public ScansionaInput(PipedInputStream pis, VerificaInput verificaInput) {
        this.pis = pis;
        this.vi = verificaInput;
    }

    public void run() {
        isRunning.set(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(pis));
        String str = null;

        while (isRunning.get()) {
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (str.equals("1234") || str.equals("1234")) {
                System.out.println("pericolo");
                vi.addStringeSospette();
            } else {
                System.out.println("ok");
            }
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
