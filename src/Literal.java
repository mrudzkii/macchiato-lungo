import java.util.Vector;

public class Literal extends Wyrazenie{
    private final int wartosc;

    // Konstruktor pozostawiam jako publiczny jako podejście z wersji 1.0
    public Literal(int wartosc) {
        this.wartosc = wartosc;
    }

    public static Literal oWartosci(int wartosc){
        return new Literal(wartosc);
    }

    @Override
    protected int wylicz(Zmienne zmienne) {
        return wartosc;
    }
}
