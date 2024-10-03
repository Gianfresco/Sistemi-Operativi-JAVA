package pkg14_07_2020.es1;

import java.io.*;

public class Esercizio1 {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        DatiAzionari da = new DatiAzionari();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserire il nome dell'indice: ");
        String indice = null;

        try {
            indice = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        da.setIndice(indice);
        RecuperaDati rd = new RecuperaDati(da);
        rd.start();

        // int valorePrecedente = 0;
        // boolean first = true;
        while (true) {
            try {
                Thread.currentThread().sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int valore = da.getValore();
            System.out.println("Main --- indice: " + da.getIndice() + ", valore: " + valore);

            if (valore > 70 || valore < 30) {
                System.out.println("Main --- valore eccessivo -> TERMINAZIONE");
                rd.terminaThread();
                break;
            }
        }

        try {
            rd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(20);
        }
        System.out.println("Main --- TERMINAZIONE");
    }
}
