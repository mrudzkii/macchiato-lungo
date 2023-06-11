import java.util.Vector;
public class For extends Blok{
    private char zmienna;
    private char zmiennaKoniecZakresu;
    private Wyrazenie wyrazenie;
    private Zmienna iterator;
    private int ileRazy;
    private int ktoryObrot;

    public For(Program program, char zmienna, Wyrazenie wyrazenie){
        super(program);
        this.nazwaInstrukcji = "For";
        poprzedniBlok = program.getBloki().elementAt(program.getBloki().size()-2);
        this.zmienna = zmienna;
        this.wyrazenie = wyrazenie;
        this.zadeklarowaneZmienne.add(new Pair<>(zmienna, new Literal(0)));
        this.ktoryObrot = 0;
    }

    public For(Program program, char zmiennaIterator, char zmiennaKoniecZakresu){
        super(program);
        this.nazwaInstrukcji = "For";
        poprzedniBlok = program.getBloki().elementAt(program.getBloki().size()-2);
        this.zmienna = zmiennaIterator;
        this.zmiennaKoniecZakresu = zmiennaKoniecZakresu;
        this.zadeklarowaneZmienne.add(new Pair<>(zmiennaIterator, new Literal(0)));
        this.ktoryObrot = 0;
        new PrzypiszWartosc(program, zmienna, new Dodawanie(zmienna, new Literal(1)));
    }

    private Zmienna znajdzZmienna(char nazwa) throws BrakZmiennejException{
        int i = 0;
        while (i < zmienne.size() && zmienne.elementAt(i).getNazwa() != nazwa){
            i++;
        }
        if(i >= zmienne.size()){
            for(Zmienna zmienna : zmienne){
                System.out.println(zmienna.getNazwa() + " = " + zmienna.getWartosc());
            }
            throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa);
        }
        return zmienne.elementAt(i);
    }

    private boolean zainicjalizuj(){
        int wartosc;
        for (Pair<Character, Wyrazenie> para : zadeklarowaneZmienne){
            try{
                wartosc = para.getSecond().wylicz(zmienne);
            } catch (DzieleniePrzezZeroException | BrakZmiennejException e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
            try{
                dodajZmienna(para.getFirst(), wartosc);
            } catch (PodwojnaDekleracjaExcepion e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
            if(para.getFirst() == zmienna)
                iterator = zmienne.lastElement();
        }

        for(Zmienna zmienna : poprzedniBlok.getZmienne()){
            if(!czyZadeklarowana(zmienna.getNazwa()) && !czyVectorZawiera(zmienne, zmienna)) zmienne.add(zmienna);
        }
        zadeklarowaneZmienne.clear();
        if(wyrazenie != null)
        {
            try {
                ileRazy = wyrazenie.wylicz(zmienne);
            } catch (DzieleniePrzezZeroException | BrakZmiennejException e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
        }
        else {
            try {
                ileRazy = znajdzZmienna(zmiennaKoniecZakresu).getWartosc();
            } catch (BrakZmiennejException e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean wykonaj(Vector<Zmienna> zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        boolean wykonano;
        wykonano = zainicjalizuj();
        if(!wykonano) return false;
        wykonano = false;
        for (int i = 0; i < ileRazy; i++) {
            iterator.ustawWartosc(i);
            for (Instrukcja instrukcja : instrukcje) {
                try{
                    wykonano = instrukcja.uruchom(zmienne);
                }
                catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e){
                    System.out.println("Błąd w instrukcji " + instrukcja.getNazwaInstrukcji());
                    wypiszWartosci();
                    e.printStackTrace();
                    return false;
                }
                if(!wykonano)
                    return false;
            }
        }
        return true;
    }

    @Override
    protected int step(Vector<Zmienna> zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        int wykonano;
        if(ktoraInstrukcja < 0){
            if (!zainicjalizuj()) {
                return -1;
            }
            ktoraInstrukcja++;
            if(instrukcje.size() == 0 || getGlebokosc() == 1) return 1;
            return 0;
        }
        else if(ktoryObrot < ileRazy) {
            iterator.ustawWartosc(ktoryObrot);
            if(ktoraInstrukcja < instrukcje.size()) {
                Instrukcja i = instrukcje.elementAt(ktoraInstrukcja);
                try {
                    wykonano = i.step(zmienne);
                    if (wykonano == 1) ktoraInstrukcja++;
                    else if (wykonano == -1) return -1;
                    if (ktoraInstrukcja >= instrukcje.size()){
                        if(ktoryObrot == ileRazy - 1){
                            ktoraInstrukcja = 0;
                            return 1;
                        }else {
                            ktoryObrot++;
                            ktoraInstrukcja = 0;
                            iterator.ustawWartosc(ktoryObrot);
                            return 0;
                        }
                    }
                    return 0;
                } catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e) {
                    System.out.println("Błąd w instrukcji " + i.getNazwaInstrukcji());
                    wypiszWartosci();
                    e.printStackTrace();
                    return -1;
                }
            }
        }

        return 1;
    }
}
