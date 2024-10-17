package esame9;

import java.util.concurrent.atomic.AtomicBoolean;

public class MacchinaA implements Runnable {
    private ControlloProduzione cp = null;
    final AtomicBoolean isRunning = new AtomicBoolean(false);

    public MacchinaA(ControlloProduzione cp) {
        this.cp = cp;
    }

    public void run() {
        isRunning.set(true);
        
        while (isRunning.get()) {
            System.out.println("Inizio lavorazione semilavorato...");
            
            try {
                int sleeptime = (int)(400 + Math.random() * 100);
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Termine lavorazione semilavorato.");
            cp.addSemilavorato();
        }
    }

    public void stopMacchinaA() {
        isRunning.set(false);
    }
}
