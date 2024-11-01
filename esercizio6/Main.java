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
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadLoad tl = new ThreadLoad();

        Monitor mnt= new Monitor(ThreadLoad tl);
        Sorter srt = new Sorter();

        Manager mng = new Manager(srt, mnt, pis);
    }
}
