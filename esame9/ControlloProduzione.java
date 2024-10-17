package esame9;

public class ControlloProduzione {
    public int semilavorati = 0;
    public int prodottifiniti = 0;

    public synchronized void addSemilavorato() {
        int tmp = semilavorati;
        tmp++;
        semilavorati = tmp;
    }

    public synchronized void subSemilavorato() {
        int tmp = semilavorati;
        tmp--;
        semilavorati = tmp;
    }

    public synchronized int getSemilavorati() {
        return semilavorati;
    }

    public synchronized void addProdottoFinito() {
        int tmp = prodottifiniti;
        tmp++;
        prodottifiniti = tmp;
    }

    public synchronized void subProdottoFinito() {
        int tmp = prodottifiniti;
        tmp--;
        prodottifiniti = tmp;
    }

    public synchronized int getProdottiFiniti() {
        return prodottifiniti;
    }
}
