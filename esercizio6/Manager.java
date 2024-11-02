package esercizio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedInputStream pis = null;
    Sorter srt = null;
    Monitor mnt = null;

    public Manager(Sorter srt, Monitor mnt, PipedInputStream pis) {
        this.pis = pis;
        this.mnt = mnt;
        this.srt = srt;
    }

    public void run() {
        isRunning.set(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(pis));

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mnt.termina();
        srt.termina();
    }    
}
