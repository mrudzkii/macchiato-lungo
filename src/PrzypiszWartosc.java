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
    protected boolean uruchom(Vector<Zmienna> zmienne) throws  DzieleniePrzezZeroException, BrakZmiennejException{
        int wartosc = wyrazenie.wylicz(zmienne);
        for(Zmienna zmienna : zmienne){
            if (zmienna.getNazwa() == this.nazwa) {
                zmienna.ustawWartosc(wartosc);
                return true;
            }
        }
        throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa + " do przypisania wartosci");
    }

    @Override
    protected boolean wykonaj(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
