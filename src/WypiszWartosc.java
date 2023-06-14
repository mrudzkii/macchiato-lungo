import java.util.Vector;

public class WypiszWartosc extends Instrukcja{
    char nazwa;
    public WypiszWartosc(char nazwa) {
        this.nazwaInstrukcji = "WypiszWartosc";
        this.nazwa = nazwa;
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        program.dodajInstrukcje(this);
    }

    @Override
    protected boolean uruchom (Zmienne zmienne) throws BrakZmiennejException{
        zmienne.wypiszWartosc(nazwa);
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
