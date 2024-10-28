package pkg24_11_2020.es2;

public class Overall {
    public int corretti = 0;
    public int difetti = 0;

    public synchronized int getCorretti() {
        return corretti;
    }

    public synchronized int getDifetti() {
        return difetti;
    }

    public synchronized void addCoretti() {
        corretti++;
    }

    public synchronized void addDifetti() {
        difetti++;
    }
}
