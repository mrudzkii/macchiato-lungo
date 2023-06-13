import java.util.Vector;
public class PrzypiszWartosc extends Instrukcja{
    private char nazwa;
    private Zmienna zmiennej;
    private Wyrazenie wyrazenie;

    public PrzypiszWartosc(Program program, char nazwa, Wyrazenie wyrazenie) {
        this.nazwaInstrukcji = "PrzypiszWartosc";
        this.nazwa = nazwa;
        this.wyrazenie = wyrazenie;
        glebokosc = program.getBloki().lastElement().glebokosc;
        program.dodajInstrukcje(this);
    }

    public PrzypiszWartosc(Program program, char nazwa, Zmienna zmiennej) {
        this.nazwaInstrukcji = "PrzypiszWartosc";
        this.nazwa = nazwa;
        this.zmiennej = zmiennej;
        glebokosc = program.getBloki().lastElement().glebokosc;
        program.dodajInstrukcje(this);
    }


    @Override
    protected boolean uruchom(Zmienne zmienne) throws  DzieleniePrzezZeroException, BrakZmiennejException{
        int wartosc = wyrazenie.wylicz(zmienne);
        zmienne.przypiszWartosc(nazwa, wartosc);
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
