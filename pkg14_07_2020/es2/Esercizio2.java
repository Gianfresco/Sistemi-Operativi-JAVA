package pkg14_07_2020.es2;

import java.io.*;

public class Esercizio2 {
    public static void main(String[] args) {
        DatiAzionari da = new DatiAzionari();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserire nome dell'indice... ");
        String indice = null;

        try {
            indice = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        da.setIndice(indice);
        RecuperaDati rd = new RecuperaDati(da);
        rd.start();

        PipedInputStream pis = new PipedInputStream();
        
        PipedOutputStream pos = null;
        try {
           pos = new PipedOutputStream(pis); 
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        AvvisaConsumatori ac = new AvvisaConsumatori(pis);
        Thread tac = new Thread(ac);
        tac.start();

        int valore = 0;

        for (int i = 0; i < 15; i++) {
            try {
                Thread.currentThread().sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            valore = da.getValore();
            System.out.println("Main --- indice: " + da.getIndice() + " valore: " + valore);

            if (valore > 70 || valore < 30) {
                Warning wrn = null;
                if (valore < 30) {
                    wrn = new Warning("Ribasso eccessivo", valore);
                } else if (valore > 70) {
                    wrn = new Warning("Rialzo eccessivo", valore);
                }

                try {
                    oos.writeObject(wrn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        rd.terminaThread();
        Warning fine = new Warning("fine", valore);

        try {
            rd.join();
            tac.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
