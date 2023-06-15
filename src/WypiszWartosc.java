import java.util.Vector;

public class WypiszWartosc extends Instrukcja{
    Wyrazenie wyrazenie;
    public WypiszWartosc(Wyrazenie wyrazenie) {
        this.nazwaInstrukcji = "WypiszWartosc";
        this.wyrazenie = wyrazenie;
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        program.dodajInstrukcje(this);
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
