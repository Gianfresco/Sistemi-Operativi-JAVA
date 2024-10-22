package esercizio5.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        float temp = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(isr);

        System.out.println("Inserire temperatura desiderata... ");
        String strTemp = "";
        try {
            strTemp = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            temp = Float.parseFloat(strTemp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(-2);
        }

        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = null;
        try {
            pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-3);
        }

        Sensor sns = new Sensor(pos);
        sns.start();

        Actuator act = new Actuator(pis, temp);
        act.start();
    }
}
