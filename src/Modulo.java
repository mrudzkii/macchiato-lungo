import java.util.Vector;

public class Modulo extends Wyrazenie{
    private Wyrazenie wyr1;
    private Wyrazenie wyr2;
    private char zmienna1;
    private char zmienna2;
    private int nrKonstruktora;

    public Modulo (Wyrazenie wyr1, Wyrazenie wyr2) {
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
        this.nrKonstruktora = 0;
    }

    public Modulo(Wyrazenie wyr, char zmienna) {
        this.wyr1 = wyr;
        this.zmienna2 = zmienna;
        this.nrKonstruktora = 1;
    }

    public Modulo(char zmienna, Wyrazenie wyrazenie){
        this.zmienna1 = zmienna;
        this.wyr2 = wyrazenie;
        this.nrKonstruktora = 2;
    }

    public Modulo(char zmienna1, char zmienna2){
        this.zmienna1 = zmienna1;
        this.zmienna2 = zmienna2;
        this.nrKonstruktora = 3;
    }

    private Zmienna znajdzZmienna(char nazwa, Vector<Zmienna> zmienne) throws BrakZmiennejException{
        int i = 0;
        while (i < zmienne.size() && zmienne.elementAt(i).getNazwa() != nazwa){
            i++;
        }
        if(i >= zmienne.size()) throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa);
        return zmienne.elementAt(i);
    }

    @Override
    protected int wylicz(Vector<Zmienna> zmienne) throws DzieleniePrzezZeroException, BrakZmiennejException{
        int tmp1;
        if(nrKonstruktora == 0 || nrKonstruktora == 1) tmp1 = wyr1.wylicz(zmienne);
        else tmp1 = znajdzZmienna(zmienna1, zmienne).wylicz(zmienne);
        int tmp2;
        if(nrKonstruktora == 0 || nrKonstruktora == 2) tmp2 = wyr2.wylicz(zmienne);
        else tmp2 = znajdzZmienna(zmienna2, zmienne).wylicz(zmienne);
        if(tmp2 == 0){
            throw new DzieleniePrzezZeroException("Dzielenie przez zero");
        }else{
            return tmp1 % tmp2;
        }
    }
}
