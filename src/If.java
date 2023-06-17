//import java.util.Vector;
//public class If extends Instrukcja {
//    protected int wart1;
//    protected int wart2;
//    protected boolean czyWykonywac;
//
//    public If(Program program, Wyrazenie wyr1, String operator, Wyrazenie wyr2) {
//        super(program);
//        glebokosc = program.getBloki().lastElement().glebokosc;
//        this.nazwaInstrukcji = "If";
//        this.wyr1 = wyr1;
//        this.wyr2 = wyr2;
//        this.operator = operator;
//        wart1 = 0;
//        wart2 = 0;
//    }
//
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
//
//    private Zmienna znajdzZmienna(char nazwa, Vector<Zmienna> zmienne) throws BrakZmiennejException{
//        int i = 0;
//        while (i < zmienne.size() && zmienne.elementAt(i).getNazwa() != nazwa){
//            i++;
//        }
//        if(i >= zmienne.size()) throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa);
//        return zmienne.elementAt(i);
//    }
//
//    private void zainicjalizuj() throws DzieleniePrzezZeroException, BrakZmiennejException{
//        this.zmienne = poprzedniBlok.getZmienne();
//        if(wyr1 != null) wart1 = wyr1.wylicz(zmienne);
//        if(wyr2 != null) wart2 = wyr2.wylicz(zmienne);
//        if(zmienna1 != ' '){
//            wart1 = zmienne.wartosc(zmienna1);
//        }
//        if(zmienna2 != ' '){
//            wart2 = zmienne.wartosc(zmienna2);
//        }
//        czyWykonywac = czyWykonac();
//    }
//
//    private boolean czyWykonac(){
//        switch (operator){
//            case "=":
//                if(wart1 == wart2)
//                    return true;
//                break;
//            case "<>":
//                if(wart1 != wart2)
//                    return true;
//                break;
//            case "<":
//                if(wart1 < wart2)
//                    return true;
//                break;
//            case ">":
//                if(wart1 > wart2)
//                    return true;
//                break;
//            case "<=":
//                if(wart1 <= wart2)
//                    return true;
//                break;
//            case ">=":
//                if(wart1 >= wart2)
//                    return true;
//                break;
//        }
//        return false;
//    }
//    @Override
//    public boolean wykonaj(Zmienne zmienneUseless) throws DzieleniePrzezZeroException, BrakZmiennejException, PodwojnaDekleracjaExcepion{
//        zainicjalizuj();
//        if(czyWykonywac){
//            for(Instrukcja i : instrukcje) i.uruchom(zmienne);
//        }
//        return true;
//    }
//
//    @Override
//    protected boolean uruchom(Zmienne zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
//        return wykonaj(zmienne);
//    }
//
//    @Override
//    protected int step(Zmienne zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
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
//        return 1;
//    }
//
//    @Override
//    protected boolean czyBlok(){
//        return false;
//    }
//}
