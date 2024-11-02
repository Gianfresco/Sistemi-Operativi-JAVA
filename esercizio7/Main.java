package esercizio7;

public class Main {
    public static void main(String[] args) {
        Macchina mcn = null;
        Produzione prod = null;

        for (int i = 0; i < 10; i++) {
            prod = new Produzione();
            mcn = new Macchina(prod);
            mcn.start();
        }

        for (int i = 0; i < 10; i++) {
            mcn.termina();
            try {
                mcn.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(prod.stampaProduzione());

        System.gc();
    }
}
