public class WypiszWartosc extends Instrukcja{
    private Wyrazenie wyrazenie;
    public WypiszWartosc(Wyrazenie wyrazenie) {
        this.nazwaInstrukcji = "WypiszWartosc";
        this.wyrazenie = wyrazenie;
    }

    @Override
    protected boolean uruchom (Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException{
        System.out.println(wyrazenie.wylicz(zmienne));
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
