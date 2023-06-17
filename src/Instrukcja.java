public abstract class Instrukcja {
    protected String nazwaInstrukcji;
    protected int glebokosc;
    protected Zmienne zmienne;
    public Zmienne getZmienne() {
        return zmienne;
    }

    public void setZmienne(Zmienne zmienne) {
        this.zmienne = zmienne;
    }

    public Blok ostatnioWykonanyBlok() { return null; }

    public void setGlebokosc(int glebokosc) {
        this.glebokosc = glebokosc;
    }

    public String getNazwaInstrukcji(){
        return nazwaInstrukcji;
    }
    protected abstract boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException;
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException{
        return true;
    }

    protected int step(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException{
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
