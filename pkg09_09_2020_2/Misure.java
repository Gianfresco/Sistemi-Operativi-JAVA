package pkg09_09_2020_2;

public class Misure {
    private float temperatura;
    private int umidita;

    public Misure(float temperatura, int umidita) {
        this.temperatura = temperatura;
        this.umidita = umidita;
    }

    public synchronized float getTemperatura() {
        return temperatura;
    }

    public synchronized int getUmidita() {
        return umidita;
    }
}
