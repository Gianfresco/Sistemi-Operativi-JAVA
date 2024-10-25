package pkg09_07_2021;

public class Main {
    public static void main(String[] args) {
        Consumi cns = new Consumi();
        SimulaConsumi simCns = new SimulaConsumi(cns);
        Thread TsimCns = new Thread(simCns);
        TsimCns.start();

        int count = 0;
        float precedente = 0.0F;
        float attuale = 0.0F;

        while (true) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            attuale = cns.getConsumi();
            System.out.println("Consumo rilevato: " + attuale);

            if (attuale > precedente) {
                if ((attuale - precedente) > (precedente * 30) / 100) {
                    System.out.println("Attenzione: variazione consumi oltre il 30%!");

                }
            }

            if (attuale > 20) {
                count++;
            } else {
                count = 0;
            }

            if (count == 3) {
                System.out.println("Rilevati 3 valori consecutivi troppo alti -> Terminazione");
                simCns.termina();
                break;
            }

            precedente = attuale;
        }

        try {
            TsimCns.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
