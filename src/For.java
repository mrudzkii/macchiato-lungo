import java.util.Vector;
public class For extends Instrukcja{
    private char zmienna;
//    private char zmiennaKoniecZakresu;
    private Wyrazenie wyrazenie;
    private Zmienna iterator;
    private int ileRazy;
    private boolean czyZainicjalizowano;
    private int ktoryObrot;

    private Blok blok; //#TODO: for ma po prostu blok tak jak procedura i jego sobie w petli wykonuje i wszystko smigga i zajebsicie
                        //#TODO: czyli musze uproscic konstruktory, wykonaj, uruchom, mozliwe ze wyjebac czesc atrybutow
                        //#TODO: przeciez ja bylem bardziej pijany piszac to niz teraz po 3 piwach XDDDDDDD

    public For(char zmienna, Wyrazenie wyrazenie, Blok blok){
        super();
        this.nazwaInstrukcji = "For";
        this.zmienna = zmienna;
        this.wyrazenie = wyrazenie;
        this.ktoryObrot = 0;
        this.blok = blok;
        this.czyZainicjalizowano = false;
    }

    @Override
    public String getNazwaInstrukcji() {
        if(!czyZainicjalizowano){
            return nazwaInstrukcji;
        }
        return blok.getNazwaInstrukcji();
    }

    //    public For(Program program, char zmiennaIterator, char zmiennaKoniecZakresu){
////        super(program);
//        this.nazwaInstrukcji = "For";
////        poprzedniBlok = program.getBloki().elementAt(program.getBloki().size()-2);
//        this.zmienna = zmiennaIterator;
//        this.zmiennaKoniecZakresu = zmiennaKoniecZakresu;
////        this.zadeklarowaneZmienne.add(new Pair<>(zmiennaIterator, new Literal(0)));
//        this.ktoryObrot = 0;
//        new PrzypiszWartosc(zmienna, new Dodawanie(zmienna, new Literal(1)));
//    }

//    private boolean zainicjalizuj(){
//        int wartosc;
//        for (Pair<Character, Wyrazenie> para : zadeklarowaneZmienne){
//            try{
//                wartosc = para.getSecond().wylicz(zmienne);
//            } catch (DzieleniePrzezZeroException | BrakZmiennejException e){
//                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
//                wypiszWartosci();
//                e.printStackTrace();
//                return false;
//            }
//            try{
//                zmienne.dodajZmienna(para.getFirst(), wartosc);
//            } catch (PodwojnaDekleracjaExcepion e){
//                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
//                wypiszWartosci();
//                e.printStackTrace();
//                return false;
//            }
//            if(para.getFirst() == zmienna)
//                iterator = zmienne.ostatnioDodana();
//        }
//        for(Zmienna zmienna : poprzedniBlok.getZmienne().getVector()){
//            if(!czyZadeklarowana(zmienna.getNazwa()) && !zmienne.czyZawiera(zmienna)) zmienne.dodajZmienna(zmienna);
//        }
//        zadeklarowaneZmienne.clear();
//        if(wyrazenie != null)
//        {
//            try {
//                ileRazy = wyrazenie.wylicz(zmienne);
//            } catch (DzieleniePrzezZeroException | BrakZmiennejException e){
//                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
//                wypiszWartosci();
//                e.printStackTrace();
//                return false;
//            }
//        }
//        else {
//            try {
//                ileRazy = zmienne.wartosc(zmiennaKoniecZakresu);
//            } catch (BrakZmiennejException e){
//                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
//                wypiszWartosci();
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    protected boolean uruchom(Zmienne zmiennePoprzednie, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException{
//        boolean wykonano;
//        wykonano = zainicjalizuj();
//        if(!wykonano) return false;
//        wykonano = false;
//        for (int i = 0; i < ileRazy; i++) {
//            iterator.ustawWartosc(i);
//            for (Instrukcja instrukcja : instrukcje) {
//                try{
//                    wykonano = instrukcja.uruchom(zmienne);
//                }
//                catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e){
//                    System.out.println("Błąd w instrukcji " + instrukcja.getNazwaInstrukcji());
//                    wypiszWartosci();
//                    e.printStackTrace();
//                    return false;
//                }
//                if(!wykonano)
//                    return false;
//            }
//        }
        zmienne = new Zmienne();
        int wartosc = wyrazenie.wylicz(zmiennePoprzednie);
        ileRazy = wyrazenie.wylicz(zmiennePoprzednie);
        for (Zmienna zmienna : zmiennePoprzednie.getVector()){
            if (zmienna.getNazwa() == this.zmienna){
                iterator = new Zmienna(this.zmienna, wartosc);
                zmienne.dodajZmienna(iterator);
            }else if(!zmienne.czyZawiera(zmienna.getNazwa())) {
                zmienne.dodajZmienna(zmienna);
            }
        }
        if(!zmienne.czyZawiera(this.zmienna)){
            iterator = new Zmienna(this.zmienna, wartosc);
            zmienne.dodajZmienna(iterator);
        }
        for (int i = 0; i < ileRazy; i++) {
            iterator.ustawWartosc(i);
            blok.wykonaj(zmienne, procedury);
        }
//        iterator.ustawWartosc(0);
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        return true;
    }

    @Override
    protected int step(Zmienne zmiennePoprzednie, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException {
//        int wykonano;
//        if(ktoraInstrukcja < 0){
//            if (!zainicjalizuj()) {
//                return -1;
//            }
//            ktoraInstrukcja++;
//            if(instrukcje.size() == 0 || getGlebokosc() == 1) return 1;
//            return 0;
//        }
//        else if(ktoryObrot < ileRazy) {
//            iterator.ustawWartosc(ktoryObrot);
//            if(ktoraInstrukcja < instrukcje.size()) {
//                Instrukcja i = instrukcje.elementAt(ktoraInstrukcja);
//                try {
//                    wykonano = i.step(zmienne);
//                    if (wykonano == 1) ktoraInstrukcja++;
//                    else if (wykonano == -1) return -1;
//                    if (ktoraInstrukcja >= instrukcje.size()){
//                        if(ktoryObrot == ileRazy - 1){
//                            ktoraInstrukcja = 0;
//                            return 1;
//                        }else {
//                            ktoryObrot++;
//                            ktoraInstrukcja = 0;
//                            iterator.ustawWartosc(ktoryObrot);
//                            return 0;
//                        }
//                    }
//                    return 0;
//                } catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e) {
//                    System.out.println("Błąd w instrukcji " + i.getNazwaInstrukcji());
//                    wypiszWartosci();
//                    e.printStackTrace();
//                    return -1;
//                }
//            }
//        }
//
//        return 1;
        if(!czyZainicjalizowano){
            zmienne = new Zmienne();
            int wartosc = wyrazenie.wylicz(zmiennePoprzednie);
            ileRazy = wyrazenie.wylicz(zmiennePoprzednie);
            for (Zmienna zmienna : zmiennePoprzednie.getVector()){
                if (zmienna.getNazwa() == this.zmienna){
                    iterator = new Zmienna(this.zmienna, wartosc);
                    iterator.ustawWartosc(0);
                    zmienne.dodajZmienna(iterator);
                }else if(!zmienne.czyZawiera(zmienna.getNazwa())){
                    zmienne.dodajZmienna(zmienna);
                }
            }
            if(!zmienne.czyZawiera(this.zmienna)){
                iterator = new Zmienna(this.zmienna, wartosc);
                zmienne.dodajZmienna(iterator);
            }
            czyZainicjalizowano = true;
            return 0;
        }
        if(ktoryObrot < ileRazy) {
            int wykonano = blok.step(zmienne, procedury);
            if(wykonano == 1 && blok.ktoraInstrukcja == blok.getInstrukcje().size()) {
                ktoryObrot++;
                iterator.ustawWartosc(ktoryObrot);
//                blok.ktoraInstrukcja = 0;
            }
            if(ktoryObrot == ileRazy) {
                ktoryObrot = 0;
                czyZainicjalizowano = false;
                iterator.ustawWartosc(0);
                return 1;
            }
            else{
                return 0;
            }
        }
        else return 1;
    }
}
