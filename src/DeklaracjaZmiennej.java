import java.util.Vector;

public class DeklaracjaZmiennej extends Instrukcja{
    private char nazwa;
    private Wyrazenie wyr;
    public DeklaracjaZmiennej(Program program, char nazwa, Wyrazenie wyr) {
        this.nazwaInstrukcji = "Deklaracja Zmiennej";
        this.nazwa = nazwa;
        this.wyr = wyr;
        program.deklaracjaZmiennej(nazwa, wyr);
        glebokosc = program.getBloki().lastElement().glebokosc;
        new PrzypiszWartosc(nazwa, wyr);
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
