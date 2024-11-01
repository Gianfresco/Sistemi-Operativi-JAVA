package esercizio6;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;
        try {
            pos = new PipedOutputStream(pis);

            ThreadLoad tl = new ThreadLoad();

            Monitor mnt = new Monitor(tl);
            Thread mntT = new Thread(mnt);
            mntT.start();

            Sorter srt = new Sorter(pos, tl);
            Thread srtT = new Thread(srt);
            srtT.start();

            Manager mng = new Manager(srt, mnt, pis);
            mng.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
