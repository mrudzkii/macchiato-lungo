public class PrzypiszWartosc extends Instrukcja{
    private char nazwa;
    private Wyrazenie wyrazenie;

    public PrzypiszWartosc(char nazwa, Wyrazenie wyrazenie) {
        this.nazwaInstrukcji = "PrzypiszWartosc";
        this.nazwa = nazwa;
        this.wyrazenie = wyrazenie;
    }


    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws  DzieleniePrzezZeroException, BrakZmiennejException{
        int wartosc = wyrazenie.wylicz(zmienne);
        zmienne.przypiszWartosc(nazwa, wartosc);
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
