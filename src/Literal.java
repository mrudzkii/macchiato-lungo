import java.util.Vector;

public class Literal extends Wyrazenie{
    private final int wartosc;

    public Literal(int wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    protected int wylicz(Vector<Zmienna> zmienne) {
        return wartosc;
    }
}