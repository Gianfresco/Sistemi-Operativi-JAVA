package pkg09_07_2021_2;

public class Consumi {
    float cns= 0.0F;

    public synchronized float getConsumi() {
        return cns;
    }

    public synchronized void setConsumi(float consumi) {
        this.cns = consumi;
    }
}
