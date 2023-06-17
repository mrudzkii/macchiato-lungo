public class IfElse extends If{

    private Blok blokElse;
    public IfElse(Wyrazenie wyr1, String operator, Wyrazenie wyr2, Blok blok, Blok blokElse){
        super(wyr1, operator, wyr2, blok);
        this.nazwaInstrukcji = "If-else";
        this.blokElse = blokElse;
    }

    @Override
    public boolean wykonaj(Zmienne zmienne, Procedury procedury) throws DzieleniePrzezZeroException, BrakZmiennejException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException, PodwojnaDeklaracjaProceduryException{
        zainicjalizuj(zmienne);
        if(czyWykonywac){
            blok.uruchom(zmienne, procedury);
        }else{
            blokElse.uruchom(zmienne, procedury);
        }
        return true;
    }

    protected int step(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException, PodwojnaDeklaracjaProceduryException {
        zainicjalizuj(zmienne);
        if(czyWykonywac)
            return blok.step(zmienne, procedury);
        else
            return blokElse.step(zmienne, procedury);
    }
}
