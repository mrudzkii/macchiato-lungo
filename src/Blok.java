import java.util.Vector;
public class Blok extends Instrukcja{
    protected Vector<Instrukcja> instrukcje;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneWczesniejZmienne;
    protected Zmienne wczesniejszeZmienne;
    protected Blok poprzedniBlok;
    protected Procedury procedury;
    protected Procedury wczesniejszeProcedury;
    protected int ktoraInstrukcja;

    @Override
    public Zmienne getZmienne() {
        return super.getZmienne();
    }

    public void setZadeklarowaneZmienne(Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne) {
        this.zadeklarowaneZmienne = zadeklarowaneZmienne;
    }


    public Procedury getProcedury() {
        return procedury;
    }

    public void setProcedury(Procedury procedury) {
        this.procedury = procedury;
    }

    public int getKtoraInstrukcja() {
        return ktoraInstrukcja;
    }

    public Vector<Instrukcja> getInstrukcje() {
        return instrukcje;
    }

    //Atrybuty konieczne do działania Instrukji if oraz else
    protected String operator;
    protected Wyrazenie wyr1;
    protected Wyrazenie wyr2;
    protected char zmienna1 = ' ';
    protected char zmienna2 = ' ';

    public Blok() {
        this.nazwaInstrukcji = "Blok";
        zmienne = new Zmienne();
        instrukcje = new Vector<>();
        zadeklarowaneZmienne = new Vector<>();
//        if(program.getBloki().size() > 0){
//            glebokosc = program.getBloki().lastElement().glebokosc + 1;
//            poprzedniBlok = program.getBloki().lastElement();
//            zadeklarowaneWczesniejZmienne = poprzedniBlok.zadeklarowaneZmienne;
//        }else{
//            glebokosc = 1;
//        }
        ktoraInstrukcja = -1;
//        program.beginBlock(this);
    }

    public Blok(Vector<Instrukcja> instrukcje, Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne){
        this.instrukcje = instrukcje;
        this.zadeklarowaneZmienne = zadeklarowaneZmienne;
        this.nazwaInstrukcji = "Blok";
        zmienne = new Zmienne();
        this.nazwaInstrukcji = "Blok";
        ktoraInstrukcja = -1;
    }

    public void deklaracjaZmiennej(char nazwa, Wyrazenie wartosc){
        zadeklarowaneZmienne.add(new Pair<Character, Wyrazenie>(nazwa, wartosc));
    }


    protected boolean czyZadeklarowana(char zmienna){
        for(Pair<Character, Wyrazenie> para : zadeklarowaneZmienne){
            if (para.getFirst() == zmienna) return true;
        }
        return false;
    }
    public void dodajInstrukcje(Instrukcja instrukcja){
        instrukcje.add(instrukcja);
    }

    @Override
    public void wypiszWartosci(){
        zmienne.wypiszWartosci();
    }

//    protected boolean czyVectorZawiera(Vector<Zmienna> wektor, Zmienna zmienna){
//        for(Zmienna element : wektor){
//            if (element == zmienna) return true;
//        }
//        return false;
//    }

    private boolean zainicjalizuj(){
        int wartosc = 0;
        for (Pair<Character, Wyrazenie> para : zadeklarowaneZmienne){
            try{
                wartosc = para.getSecond().wylicz(zmienne);
            } catch (DzieleniePrzezZeroException e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            } catch (BrakZmiennejException e){
                try {
                    wartosc = para.getSecond().wylicz(wczesniejszeZmienne);
                } catch (DzieleniePrzezZeroException | BrakZmiennejException e1){
                    System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                    wypiszWartosci();
                    e1.printStackTrace();
                    return false;
                }
            }
            try{
                zmienne.dodajZmienna(para.getFirst(), wartosc);
            } catch (PodwojnaDekleracjaExcepion e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
        }
        for(Zmienna zmienna : wczesniejszeZmienne.getVector()){
            if(!czyZadeklarowana(zmienna.getNazwa()) && !zmienne.czyZawiera(zmienna)) zmienne.dodajZmienna(zmienna);
        }
        zadeklarowaneZmienne.clear();

        for(Procedura p : wczesniejszeProcedury.getProcedury()){
            if(!procedury.czyIstnieje(p.getNazwa()))
                procedury.dodajProcedure(p);
        }
        return true;
    }
    @Override
    protected boolean wykonaj(Zmienne wczesniejszeZmienne, Procedury wczesniejszeProcedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException{
        this.wczesniejszeZmienne = wczesniejszeZmienne;
        this.wczesniejszeProcedury = wczesniejszeProcedury;
        boolean wykonano = zainicjalizuj();
        if(!wykonano) return false;
        wykonano = false;
        for(Instrukcja i : instrukcje){
            try{
                wykonano = i.uruchom(zmienne, procedury);
            }
            catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e){
                System.out.println("Błąd w instrukcji " + i.getNazwaInstrukcji());
                wypiszWartosci();
                e.printStackTrace();
                wykonano = false;
                return false;
            }
            if(!wykonano)
                break;
        }
        return wykonano;
    }

    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        return wykonaj(zmienne, procedury);
    }

    @Override
    protected int step(Zmienne zmienneUseless, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        int wykonano;
        if(ktoraInstrukcja < 0){
            if (!zainicjalizuj()) {
                return -1;
            }
            ktoraInstrukcja++;
            if(instrukcje.size() == 0 || getGlebokosc() == 1) return 1;
            return 0;
        }
        else if(ktoraInstrukcja < instrukcje.size()) {
            Instrukcja i = instrukcje.elementAt(ktoraInstrukcja);
            try {
                wykonano = i.step(zmienne, procedury);
                if(wykonano == 1) ktoraInstrukcja++;
                else if(wykonano == -1) return -1;
                if(ktoraInstrukcja >= instrukcje.size()) {
                    ktoraInstrukcja = 0;
                    return 1;
                }
                return 0;
            } catch (DzieleniePrzezZeroException | BrakZmiennejException | PodwojnaDekleracjaExcepion e) {
                System.out.println("Błąd w instrukcji " + i.getNazwaInstrukcji());
                wypiszWartosci();
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }

    @Override
    public String getNazwaInstrukcji() {
        if(ktoraInstrukcja < 0)
            return this.nazwaInstrukcji;
        else
            return instrukcje.elementAt(ktoraInstrukcja).getNazwaInstrukcji();
    }

    @Override
    protected boolean czyBlok(){
        return true;
    }
}
