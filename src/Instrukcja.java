import java.util.Vector;

public abstract class Instrukcja {
    protected String nazwaInstrukcji;
    protected int glebokosc;
    protected Vector<Zmienna> zmienne;

    public Vector<Zmienna> getZmienne() {
        return zmienne;
    }

    public String getNazwaInstrukcji(){
        return nazwaInstrukcji;
    }
    protected abstract boolean wykonaj(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion;
    protected boolean uruchom(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion{
        return true;
    }

    protected int step(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion{
        if(this.uruchom(zmienne)) return 1;
        else return -1;
    }

    protected boolean czyBlok(){
        return false;
    }

    public int getGlebokosc() {
        return glebokosc;
    }

    public void wypiszWartosci(){    }
}
