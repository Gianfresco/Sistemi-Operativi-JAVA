package esercizio6;

public class ThreadLoad {
    private int id = 0;
    private double load = 0;

    public synchronized int getId() {
        return id;
    }

    public synchronized double getLoad() {
        return load;
    }

    public synchronized void setId(int id) {
        this.id = id;
    } 

    public synchronized void setLoad(double load) {
        this.load = load;
    }
}
