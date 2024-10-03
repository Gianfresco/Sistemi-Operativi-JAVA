package pkg19_06_2020.es2;

public class VerificaInput {
    private int stringheSospette = 0;

    public synchronized void addStrigheSospette() {
        stringheSospette++;
    }

    public synchronized int getStringheSospette() {
        return stringheSospette;
    }
}
