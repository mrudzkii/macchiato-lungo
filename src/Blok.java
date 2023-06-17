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
    private Instrukcja ostatnioWykonana;

    @Override
    public Zmienne getZmienne() {
        return super.getZmienne();
    }

    public void setZadeklarowaneZmienne(Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne) {
        this.zadeklarowaneZmienne = zadeklarowaneZmienne;
    }

    @Override
    public Blok ostatnioWykonanyBlok(){
        if(ostatnioWykonana.czyBlok() && ostatnioWykonana != this)
            return ostatnioWykonana.ostatnioWykonanyBlok();
        else
            return this;
    }

    public void setPoprzedniBlok(Blok poprzedniBlok) {
        this.poprzedniBlok = poprzedniBlok;
    }

    public Blok xWstecz(int x){
        if(x == 0){
            return this;
        }
        return poprzedniBlok.xWstecz(x - 1);
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

    private Vector<Blok> blokiWczesniejsze;

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
        blokiWczesniejsze = new Vector<>();
        ktoraInstrukcja = -1;
        this.wczesniejszeZmienne = new Zmienne();
        this.wczesniejszeProcedury = new Procedury();
    }

    public void setBlokiWczesniejsze(Vector<Blok> blokiWczesniejsze) {
        this.blokiWczesniejsze = blokiWczesniejsze;
    }

    public void addBlokiWczesniejsze(Blok blok){
        this.blokiWczesniejsze.add(blok);
    }

    public Blok(Vector<Instrukcja> instrukcje, Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne){
        this.instrukcje = instrukcje;
        this.zadeklarowaneZmienne = zadeklarowaneZmienne;
        this.nazwaInstrukcji = "Blok";
        zmienne = new Zmienne();
        this.nazwaInstrukcji = "Blok";
        ktoraInstrukcja = -1;
        this.wczesniejszeZmienne = new Zmienne();
        this.wczesniejszeProcedury = new Procedury();
    }

    public void deklaracjaZmiennej(char nazwa, Wyrazenie wartosc){
        zadeklarowaneZmienne.add(new Pair<>(nazwa, wartosc));
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

    private boolean zainicjalizuj(){
        int wartosc;
        ostatnioWykonana = this;
        zmienne = new Zmienne();
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
        this.wczesniejszeZmienne.setZmienne(wczesniejszeZmienne.getVector());
        this.wczesniejszeProcedury.procedury = wczesniejszeProcedury.procedury; //#TODO getter setter
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
    protected int step(Zmienne wczesniejszeZmienne, Procedury wczesniejszeProcedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        int wykonano;
        if(ktoraInstrukcja < 0){
            this.wczesniejszeZmienne.setZmienne(wczesniejszeZmienne.getVector());
            this.wczesniejszeProcedury.procedury = wczesniejszeProcedury.procedury; //#TODO getter setter
            if (!zainicjalizuj()) {
                return -1;
            }
            ktoraInstrukcja++;
            if(instrukcje.size() == 0 || getGlebokosc() == 0) return 1;
            return 0;
        }
        if(ktoraInstrukcja == instrukcje.size())
            ktoraInstrukcja = 0;
        if(ktoraInstrukcja < instrukcje.size()) {
            Instrukcja i = instrukcje.elementAt(ktoraInstrukcja);
            try {
                wykonano = i.step(zmienne, procedury);
                ostatnioWykonana = i;
                if(wykonano == 1) ktoraInstrukcja++;
                else if(wykonano == -1) return -1;
                if(ktoraInstrukcja >= instrukcje.size()) {
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
            return instrukcje.elementAt(ktoraInstrukcja % instrukcje.size()).getNazwaInstrukcji();
    }

    @Override
    protected boolean czyBlok(){
        return true;
    }
}
