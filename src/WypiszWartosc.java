import java.util.Vector;

public class WypiszWartosc extends Instrukcja{
    char nazwa;
    public WypiszWartosc(Program program, char nazwa) {
        this.nazwaInstrukcji = "WypiszWartosc";
        this.nazwa = nazwa;
        glebokosc = program.getBloki().lastElement().glebokosc;
        program.dodajInstrukcje(this);
    }

    @Override
    protected boolean uruchom (Vector<Zmienna> zmienne) throws BrakZmiennejException{
        for(Zmienna zmienna : zmienne){
            if(zmienna.getNazwa() == nazwa){
                System.out.println(zmienna.getWartosc());
                return true;
            }
        }
        throw new BrakZmiennejException("Nie znaleziono zmiennej '" + nazwa + "' do wypisania");
    }

    @Override
    protected boolean wykonaj(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
