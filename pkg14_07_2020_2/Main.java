package pkg14_07_2020_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
            System.exit(-1);
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(pos);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        
        Warning wrn = null;
        DatiAzionari da = new DatiAzionari();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        RecuperaDati ra = new RecuperaDati(da);
        ra.start();

        AvvisaConsumatori ac = new AvvisaConsumatori(pis);
        Thread acT = new Thread(ac);
        acT.start();

        System.out.println("Inserire indice... ");
        String indice = null;
        try {
            indice = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(7 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int val = da.getValore();
            if (val < 30) {
                wrn = new Warning(val, "ribasso eccessivo");
            } else {
                wrn = new Warning(val, "rialzo eccessivo");
            }

            try {
                oos.writeObject(wrn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ra.termina();
        wrn = new Warning(0, "fine");
        try {
            oos.writeObject(wrn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            acT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}