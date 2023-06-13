import java.util.Vector;
public class Blok extends Instrukcja{
    protected Vector<Instrukcja> instrukcje;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneWczesniejZmienne;
    protected Blok poprzedniBlok;
    protected int ktoraInstrukcja;

    @Override
    public Zmienne getZmienne() {
        return super.getZmienne();
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

    public Blok(Program program) {
        this.nazwaInstrukcji = "Blok";
        zmienne = new Zmienne();
        instrukcje = new Vector<Instrukcja>();
        zadeklarowaneZmienne = new Vector<Pair<Character, Wyrazenie>>();
        zadeklarowaneWczesniejZmienne = new Vector<Pair<Character, Wyrazenie>>();
        if(program.getBloki().size() > 0){
            glebokosc = program.getBloki().lastElement().glebokosc + 1;
            poprzedniBlok = program.getBloki().lastElement();
            zadeklarowaneWczesniejZmienne = poprzedniBlok.zadeklarowaneZmienne;
        }else{
            glebokosc = 1;
        }
        ktoraInstrukcja = -1;
        program.beginBlock(this);
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

    protected boolean czyVectorZawiera(Vector<Zmienna> wektor, Zmienna zmienna){
        for(Zmienna element : wektor){
            if (element == zmienna) return true;
        }
        return false;
    }

    private boolean zainicjalizuj(){
        int wartosc = 0;
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
                zmienne.dodajZmienna(para.getFirst(), wartosc);
            } catch (PodwojnaDekleracjaExcepion e){
                System.out.println("Błąd w instrukcji DeklaracjaZmiennej");
                wypiszWartosci();
                e.printStackTrace();
                return false;
            }
        }
        if (poprzedniBlok != null){
            for(Zmienna zmienna : poprzedniBlok.getZmienne().getVector()){
                if(!czyZadeklarowana(zmienna.getNazwa()) && !zmienne.czyZawiera(zmienna)) zmienne.dodajZmienna(zmienna);
            }
        }
        zadeklarowaneZmienne.clear();
        return true;
    }
    @Override
    protected boolean wykonaj(Zmienne zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion{
        boolean wykonano = zainicjalizuj();
        if(!wykonano) return false;
        wykonano = false;
        for(Instrukcja i : instrukcje){
            try{
                wykonano = i.uruchom(zmienne);
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
    protected boolean uruchom(Zmienne zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return wykonaj(zmienne);
    }

    @Override
    protected int step(Zmienne zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion{
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
                wykonano = i.step(zmienne);
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
