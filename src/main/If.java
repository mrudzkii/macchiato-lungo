public class If extends Instrukcja {
    protected int wart1, wart2;
    protected Wyrazenie wyr1, wyr2;
    protected boolean czyWykonywac;
    private String operator;
    protected Blok blok;

    public If(Wyrazenie wyr1, String operator, Wyrazenie wyr2, Blok blok) {
        super();
        this.nazwaInstrukcji = "If";
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
        this.operator = operator;
        this.blok = blok;
        wart1 = 0;
        wart2 = 0;
    }

    protected void zainicjalizuj(Zmienne zmienne) throws DzieleniePrzezZeroException, BrakZmiennejException{
        wart1 = wyr1.wylicz(zmienne);
        wart2 = wyr2.wylicz(zmienne);
        czyWykonywac = czyWykonac();
    }

    private boolean czyWykonac(){
        switch (operator){
            case "=":
                if(wart1 == wart2)
                    return true;
                break;
            case "<>":
                if(wart1 != wart2)
                    return true;
                break;
            case "<":
                if(wart1 < wart2)
                    return true;
                break;
            case ">":
                if(wart1 > wart2)
                    return true;
                break;
            case "<=":
                if(wart1 <= wart2)
                    return true;
                break;
            case ">=":
                if(wart1 >= wart2)
                    return true;
                break;
        }
        return false;
    }
    @Override
    public boolean wykonaj(Zmienne zmienne, Procedury procedury) throws DzieleniePrzezZeroException, BrakZmiennejException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException, PodwojnaDeklaracjaProceduryException{
        zainicjalizuj(zmienne);
        if(czyWykonywac){
            blok.uruchom(zmienne, procedury);
        }
        return true;
    }

    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException, PodwojnaDeklaracjaProceduryException {
        return wykonaj(zmienne, procedury);
    }

    @Override
    protected int step(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException, PodwojnaDeklaracjaProceduryException {
        if(czyWykonywac)
            return blok.step(zmienne, procedury);
        return 1;
    }

    @Override
    protected boolean czyBlok(){
        return false;
    }
}
