import java.util.Vector;

public abstract class Wyrazenie {
    protected abstract int wylicz(Vector<Zmienna> zmienne) throws DzieleniePrzezZeroException, BrakZmiennejException;

}
