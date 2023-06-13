import java.util.Vector;

public class Zmienna extends Wyrazenie {
    private int wartosc;
    private char nazwa;

    protected char getNazwa(){
        return this.nazwa;
    }

    public Zmienna(char nazwa, int wartosc) {
        this.wartosc = wartosc;
        this.nazwa = nazwa;
    }

    private Zmienna znajdzZmienna(char nazwa, Vector<Zmienna> zmienne) throws BrakZmiennejException{
        int i = 0;
        while (i < zmienne.size() && zmienne.elementAt(i).nazwa != nazwa){
            i++;
        }
        if(i >= zmienne.size()) throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa);
        return zmienne.elementAt(i);
    }

    protected int getWartosc(){
        return wartosc;
    }

    @Override
    protected int wylicz(Zmienne zmienne) throws BrakZmiennejException {
        return zmienne.wartosc(nazwa);
    }

    public void ustawWartosc(int wartosc){
        this.wartosc = wartosc;
    }
}
