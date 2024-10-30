package pkg19_06_2020_2;

public class VerificaInput {
    int stringSosp;
    
    public synchronized void addStringeSospette() {
        stringSosp++;
    }

    public synchronized int getNumStringheSospette() {
        return stringSosp;
    }
}
