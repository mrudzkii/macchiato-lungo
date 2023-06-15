import java.util.Vector;

public abstract class Instrukcja {
    protected String nazwaInstrukcji;
    protected int glebokosc;
//    protected Vector<Zmienna> zmienne;
    protected Zmienne zmienne;
    public Zmienne getZmienne() {
        return zmienne;
    }

    public void setZmienne(Zmienne zmienne) {
        this.zmienne = zmienne;
    }

    public String getNazwaInstrukcji(){
        return nazwaInstrukcji;
    }
    protected abstract boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException;
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        return true;
    }

    protected int step(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        if(this.uruchom(zmienne, procedury)) return 1;
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
