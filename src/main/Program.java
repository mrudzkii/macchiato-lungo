import java.util.Vector;
public class Program {
    private Vector<Blok> bloki;
    private Blok zewnetrzny;

    public Program(){
        bloki = new Vector<>();
    }

    public Program(Blok mainBlok){
        zewnetrzny = mainBlok;
    }

    public Vector<Blok> getBloki() {
        return bloki;
    }

    public Blok getZewnetrzny(){
        return zewnetrzny;
    }

    public void deklaracjaZmiennej (char nazwa, Wyrazenie wyr){
        bloki.lastElement().deklaracjaZmiennej(nazwa, wyr);
    }

    public void dodajInstrukcje (Instrukcja instrukcja){
        bloki.lastElement().dodajInstrukcje(instrukcja);
    }

    public void uruchomBezDebugowania(){
            try {
                zewnetrzny.wykonaj(new Zmienne(), new Procedury());
                zewnetrzny.wypiszWartosci();

            } catch (Exception e) {
                System.out.println("Blad wykonania");
                e.printStackTrace();
            }
    }

    public void uruchomZOdpluskiwaniem(){
        Odpluskwiacz debuger = new Odpluskwiacz(zewnetrzny);
        debuger.uruchom();
    }
}
