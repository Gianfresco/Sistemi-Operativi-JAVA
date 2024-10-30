package pkg24_11_2020_2;

public class Overall {
    private int prodottiCorretti = 0;
    private int prodottiDifettosi = 0;

    public synchronized int getDifettosi() {
        return prodottiDifettosi;
    }

    public synchronized int getCorretti() {
        return prodottiCorretti;
    }

    public synchronized void addDifettosi() {
        prodottiDifettosi++;
    }

    public synchronized void addCorretti() {
        prodottiCorretti++;
    }
}
