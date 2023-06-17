public class Dodawanie extends Wyrazenie{
    private Wyrazenie wyr1;
    private Wyrazenie wyr2;
    private char zmienna1;
    private char zmienna2;
    private int nrKonstruktora;

    public Dodawanie (Wyrazenie wyr1, Wyrazenie wyr2) {
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
        this.nrKonstruktora = 0;
    }

    public static Dodawanie wyrazen(Wyrazenie wyr1, Wyrazenie wyr2) {
        return new Dodawanie(wyr1, wyr2);
    }

    public Dodawanie(Wyrazenie wyr, char zmienna) {
        this.wyr1 = wyr;
        this.zmienna2 = zmienna;
        this.nrKonstruktora = 1;
    }

    public static Dodawanie wyrazen(Wyrazenie wyr, char zmienna){
        return new Dodawanie(wyr, zmienna);
    }
    public Dodawanie(char zmienna, Wyrazenie wyrazenie){
        this.zmienna1 = zmienna;
        this.wyr2 = wyrazenie;
        this.nrKonstruktora = 2;
    }

    public static Dodawanie wyrazen(char zmienna, Wyrazenie wyrazenie){
        return new Dodawanie(zmienna, wyrazenie);
    }

    public Dodawanie(char zmienna1, char zmienna2){
        this.zmienna1 = zmienna1;
        this.zmienna2 = zmienna2;
        this.nrKonstruktora = 3;
    }

    public static Dodawanie wyrazen(char zmienna1, char zmienna2){
        return new Dodawanie(zmienna1, zmienna2);
    }

    @Override
    protected int wylicz(Zmienne zmienne) throws DzieleniePrzezZeroException, BrakZmiennejException {
        int tmp1;
        if(nrKonstruktora == 0 || nrKonstruktora == 1) tmp1 = wyr1.wylicz(zmienne);
        else tmp1 = zmienne.wartosc(zmienna1);
        int tmp2;
        if(nrKonstruktora == 0 || nrKonstruktora == 2) tmp2 = wyr2.wylicz(zmienne);
        else tmp2 = zmienne.wartosc(zmienna2);
        return tmp1 + tmp2;
    }
}
