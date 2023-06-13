import java.util.Vector;

public abstract class Wyrazenie {
    protected abstract int wylicz(Zmienne zmienne) throws DzieleniePrzezZeroException, BrakZmiennejException;

}
