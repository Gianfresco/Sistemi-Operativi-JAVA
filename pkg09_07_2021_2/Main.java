package pkg09_07_2021_2;

public class Main {
    public static void main(String[] args) {
        Consumi cons = new Consumi();
        int count = 0;
        float consPrec = 0.0F;

        SimulaConsumi sc = new SimulaConsumi(cons);
        Thread Tsc = new Thread(sc);
        Tsc.start();

        while (true) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println(cons.getConsumi());
            if (cons.getConsumi() > (consPrec + (consPrec / 100 * 30))) {
                System.out.println("Attenzione ai consumi!");
            }
    
            if (cons.getConsumi() > 20) {
                count++;
                if (count == 3) {
                    break;
                }
            }

            consPrec = cons.getConsumi();
        }

        sc.termina();

        try {
            Tsc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
