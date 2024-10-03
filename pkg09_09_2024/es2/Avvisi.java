package pkg09_09_2024.es2;

import java.util.concurrent.atomic.AtomicBoolean;

public class Avvisi extends Thread {
    private Storico storico = null;
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public Avvisi (Storico storico) {
        this.storico = storico;
    }

    public void run() {
        isRunning.set(true);

        while (isRunning.get()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // recupero valore cambi repentini
            int cambiRepentini = storico.getCambiRepentini();
            System.out.println("Attenzione: " + cambiRepentini);
        }
    }

    public void finisci() {
        isRunning.set(false);
    }
    
}
