package es2;

public class VerificaInput {
    private int stringheSospette = 0;

    public synchronized void addStringheSospette() {
        stringheSospette++;
    }

    public synchronized int getStringheSospette() {
        return stringheSospette;
    }
}
