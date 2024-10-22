package esercizio5.buffered;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Actuator extends Thread {
    private PipedInputStream pis = null;
    private float objTemp = 0;

    public Actuator(PipedInputStream pis, float objTemp) {
        this.pis = pis;
        this.objTemp = objTemp;
    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(pis);
            while (true) {
                float tmp = dis.readFloat();
                if (tmp < objTemp) {
                    System.out.println("Accendere il riscaldamento - " + tmp + "°C");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
