import java.util.Vector;
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

//    public If(Program program, char zmienna1, String operator, Wyrazenie wyr2) {
//        super(program);
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        this.nazwaInstrukcji = "If";
//        this.zmienna1 = zmienna1;
//        this.wyr2 = wyr2;
//        this.operator = operator;
//        wart1 = 0;
//        wart2 = 0;
//    }
//
//    public If(Program program, Wyrazenie wyr1, String operator, char zmienna2) {
//        super(program);
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        this.nazwaInstrukcji = "If";
//        this.zmienna2 = zmienna2;
//        this.wyr1 = wyr1;
//        this.operator = operator;
//        wart1 = 0;
//        wart2 = 0;
//    }
//
//    public If(Program program, char zmienna1, String operator, char zmienna2) {
//        super(program);
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        this.nazwaInstrukcji = "If";
//        this.zmienna1 = zmienna1;
//        this.zmienna2 = zmienna2;
//        this.operator = operator;
//        wart1 = 0;
//        wart2 = 0;
//    }

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
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException{
        zainicjalizuj(zmienne);
        if(czyWykonywac){
            blok.uruchom(zmienne, procedury);
        }
        return true;
    }

    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException {
        return wykonaj(zmienne, procedury);
    }

    @Override
    protected int step(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, ZlaLiczbaParametrowException, BrakProceduryException {
//        int wykonano;
//        if(ktoraInstrukcja < 0) {
//            zainicjalizuj();
//            ktoraInstrukcja++;
//            if(instrukcje.size() == 0 || getGlebokosc() == 1) return 1;
//            return 0;
//        }
//        else if(ktoraInstrukcja < instrukcje.size() && czyWykonywac) {
//            Instrukcja i = instrukcje.elementAt(ktoraInstrukcja);
//            try {
//                wykonano = i.step(zmienne);
//                if(wykonano == 1) ktoraInstrukcja++;
//                else if(wykonano == -1) return -1;
//                if(ktoraInstrukcja >= instrukcje.size()) {
//                    ktoraInstrukcja = -1;
//                    return 1;
//                }
//                return 0;
//            } catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e) {
//                System.out.println("Błąd w instrukcji " + i.getNazwaInstrukcji());
//                wypiszWartosci();
//                e.printStackTrace();
//                return -1;
//            }
//        }
//        ktoraInstrukcja = -1;
        if(czyWykonywac)
            return blok.step(zmienne, procedury);
        return 1;
    }

    @Override
    protected boolean czyBlok(){
        return false;
    }
}
