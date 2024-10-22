package esercizio5.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.nio.charset.Charset;

public class Actuator extends Thread {
    private PipedInputStream pis = null;
    private float objTemp = 0;

    public Actuator(PipedInputStream pis, float objTemp) {
        this.objTemp = objTemp;
        this.pis = pis;
    }

    public void run() {
        byte[] buffer = new byte[128];
        int nread = 0;

        try {
            while ((nread = pis.read(buffer)) > 0) {
                String rec = new String(buffer, 0, nread, Charset.forName("UTF-8"));
                float tmp = Float.parseFloat(rec);

                if (tmp < objTemp) {
                    System.out.println("Accendere il riscaldamento - " + tmp + "°C");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
