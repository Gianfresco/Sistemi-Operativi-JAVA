package pkg14_07_2020_2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.io.PipedOutputStream;
import java.util.Random;

public class RecuperaDati extends Thread {
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    PipedOutputStream pos = new PipedOutputStream();
    DatiAzionari da = null;
    Random rnd = new Random();

    public RecuperaDati(DatiAzionari da) {
        this.da = da;
    }

    public void run() {
        isRunning.set(true);
        int idxPrec = 0;

        while (isRunning.get()) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            int val = 80 * rnd.nextInt() + 20;
            da.setValore(val);

            if (da.getIndice() != idxPrec) {
                System.out.println("attenzione!");
            }

            idxPrec = da.getIndice();
        }
    }

    public void termina() {
        isRunning.set(false);
    }
}
