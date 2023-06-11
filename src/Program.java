import java.util.Scanner;
import java.util.Vector;
public class Program {
    private Vector<Blok> bloki;
    private Blok zewnetrzny;

    public Program(){
        bloki = new Vector<Blok>();
    }

    public void beginBlock(Blok blok){
        bloki.add(blok);
        if(bloki.size() == 1) {
            zewnetrzny = blok;
        }
//        dodajInstrukcje(blok);
//        System.out.println("dodano blok");
    }

    public Vector<Blok> getBloki() {
        return bloki;
    }

    public void endBlock(){
        if(bloki.size() > 1) {
//            Blok przedostatni = bloki.elementAt(bloki.indexOf(bloki.lastElement())-1);
            Blok ostatni = bloki.lastElement();
//            for(Instrukcja i : ostatni.getInstrukcje()){
//                przedostatni.dodajInstrukcje(i);
//            }
            bloki.remove(bloki.lastElement());
            bloki.lastElement().dodajInstrukcje(ostatni);
        }

    }

    public void deklaracjaZmiennej (char nazwa, Wyrazenie wyr){
        bloki.lastElement().deklaracjaZmiennej(nazwa, wyr);
    }

    public void dodajInstrukcje (Instrukcja instrukcja){
        bloki.lastElement().dodajInstrukcje(instrukcja);
    }

    public void uruchomBezDebugowania(){
        boolean wykonano;
            try {
                wykonano = zewnetrzny.wykonaj(new Vector<>());
                zewnetrzny.wypiszWartosci();

            } catch (BrakZmiennejException | DzieleniePrzezZeroException | PodwojnaDekleracjaExcepion e) {
                System.out.println("Blad wykonania");
                e.printStackTrace();
            }
//        }
    }

    public void uruchomZOdpluskiwaniem(){
        String input;
        Scanner wciagara = new Scanner(System.in);
        int wynik = 2;
        int parametr;
        while (true){
            input = wciagara.next();
            switch (input){
                case "s":
                    parametr = wciagara.nextInt();
                    if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()){
                        System.out.println("skonczyly sie instrukcje do wykonania");
                        break;
                    }
                    while (parametr > 0) {
                        try {
                            wynik = zewnetrzny.step(new Vector<>());
                            if(zewnetrzny.getKtoraInstrukcja() == zewnetrzny.getInstrukcje().size())
                                zewnetrzny.wypiszWartosci();
                        } catch (BrakZmiennejException | DzieleniePrzezZeroException | PodwojnaDekleracjaExcepion e) {
                            System.out.println("Nastapil blad wykonywania");
                            break;
                        }
                        if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()) {
                            System.out.println("skonczyly sie instrukcje do wykonania");
                        } else if (wynik == -1) {
                            System.out.println("blad wykonania");
                        }
                        parametr--;
                    }
                    if(zewnetrzny.getKtoraInstrukcja() == -1){
                        System.out.println("Nastepna instrukcja: Blok (pierwszy blok programu)");
                    }
                    else if(zewnetrzny.getKtoraInstrukcja() < zewnetrzny.getInstrukcje().size()){
                        System.out.println("Nastepna instrukcja: " +
                                zewnetrzny.getInstrukcje().elementAt(zewnetrzny.getKtoraInstrukcja()).getNazwaInstrukcji());
                    } else {
                        System.out.println("Nie ma nastepnej instrukcji do wykonania");
                    }
                    break;
                case "c":
                    if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()){
                        System.out.println("Program sie juz zakonczyl");
                        break;
                    }
                    while (zewnetrzny.ktoraInstrukcja < zewnetrzny.getInstrukcje().size()){
                        try {
                            wynik = zewnetrzny.step(new Vector<>());
                            if(zewnetrzny.getKtoraInstrukcja() == zewnetrzny.getInstrukcje().size())
                                zewnetrzny.wypiszWartosci();
                        } catch (BrakZmiennejException | DzieleniePrzezZeroException | PodwojnaDekleracjaExcepion e) {
                            System.out.println("Nastapil blad wykonywania");
                            break;
                        }
                        if(wynik == -1){
                            System.out.println("nastapil blad wykonania");
                        }
                    }
                    break;
                case "e":
                    return;
                case "d":
                    parametr = wciagara.nextInt();
                    Instrukcja i = zewnetrzny.getInstrukcje().elementAt(
                             Math.min(zewnetrzny.getKtoraInstrukcja(), zewnetrzny.instrukcje.size()-1));
                    int poczGlebokosc = i.getGlebokosc();
                    if(parametr > poczGlebokosc){
                        System.out.println("Podany parametr jest wiekszy od poziomu zagniezdzenia obecnej instrukcji");
                        break;
                    }
                    int tmp = 1;
                    if(poczGlebokosc - parametr == 1){
                        zewnetrzny.wypiszWartosci();
                        break;
                    }
                    while (!i.czyBlok() || i.getGlebokosc() != poczGlebokosc-parametr){
                        i = zewnetrzny.getInstrukcje().elementAt(zewnetrzny.getKtoraInstrukcja() - tmp);
                        tmp++;
                    }
                    System.out.println(i.getNazwaInstrukcji());
                    i.wypiszWartosci();
                    break;
            }
        }
    }
}
