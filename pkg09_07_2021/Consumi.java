package pkg09_07_2021;

public class Consumi {
    private float consumi = 0.0F;

    public synchronized float getConsumi() {
        return consumi;
    }

    public synchronized void setConsumi(float consumi) {
        this.consumi = consumi;
    }
}
