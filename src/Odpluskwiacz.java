import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Odpluskwiacz {
    private Blok zewnetrzny;
    private Blok obceny;
    int wynik;

    public Odpluskwiacz(Blok zewnetrzny) {
        this.zewnetrzny = zewnetrzny;
        this.obceny = zewnetrzny;
    }

    private void step(int parametr){
        if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()) {
            System.out.println("skonczyly sie instrukcje do wykonania");
            return;
        }
        while (parametr > 0) {
            try {
                wynik = zewnetrzny.step(new Zmienne(), new Procedury());
                obceny = zewnetrzny.ostatnioWykonanyBlok();
                if (zewnetrzny.getKtoraInstrukcja() == zewnetrzny.getInstrukcje().size()) {
                    zewnetrzny.wypiszWartosci();
                }
            } catch (Exception e) {
                System.out.println("Nastapil blad wykonywania");
                e.printStackTrace();
                break;
            }
            if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()) {
                System.out.println("skonczyly sie instrukcje do wykonania");
            } else if (wynik == -1) {
                System.out.println("blad wykonania");
            }
            parametr--;
        }
        if (zewnetrzny.getKtoraInstrukcja() == -1) {
            System.out.println("Nastepna instrukcja: Blok (pierwszy blok programu)");
        } else if (zewnetrzny.getKtoraInstrukcja() < zewnetrzny.getInstrukcje().size()) {
            System.out.println("Nastepna instrukcja: " +
                    zewnetrzny.getInstrukcje().elementAt(zewnetrzny.getKtoraInstrukcja()).getNazwaInstrukcji());
        } else {
            System.out.println("Nie ma nastepnej instrukcji do wykonania");
        }
    }

    private void conti(){
        if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()) {
            System.out.println("Program sie juz zakonczyl");
            return;
        }
        while (zewnetrzny.ktoraInstrukcja < zewnetrzny.getInstrukcje().size()) {
            try {
                wynik = zewnetrzny.step(new Zmienne(), new Procedury());
                if (zewnetrzny.getKtoraInstrukcja() == zewnetrzny.getInstrukcje().size())
                    zewnetrzny.wypiszWartosci();
            } catch (Exception e) {
                System.out.println("Nastapil blad wykonywania");
                break;
            }
            if (wynik == -1) {
                System.out.println("nastapil blad wykonania");
            }
        }
    }

    private void display(int parametr){
        if(zewnetrzny.ktoraInstrukcja >= zewnetrzny.getInstrukcje().size()){
            System.out.println("Program sie juz zakonczyl");
            return;
        }
        int poczGlebokosc = obceny.getGlebokosc();
        if (parametr > poczGlebokosc) {
            System.out.println("Podany parametr jest wiekszy od poziomu zagniezdzenia obecnej instrukcji");
            return;
        }
        Blok i = obceny.xWstecz(parametr);
        System.out.println(i.getNazwaInstrukcji());
        i.wypiszWartosci();
    }

    private void dump(String sciezka){
        if (zewnetrzny.ktoraInstrukcja >= zewnetrzny.instrukcje.size()) {
            System.out.println("Program się zakończył (nie zmodyfikowano pliku)");
            return;
        }
        File plik = new File(sciezka);
        try {
            FileWriter wypluwara = new FileWriter(plik);
            wypluwara.write("Zadeklarowane procedury: \n");
            for(Procedura p : obceny.getProcedury().getProcedury()){
                wypluwara.write(p.getNazwa() + "(");
                for(char nazwa : p.getNazwyLista()){
                    wypluwara.write(nazwa);
                    if(nazwa != p.getNazwyLista().get(p.getNazwyLista().size()-1))
                        wypluwara.write(", ");
                }
                wypluwara.write(")\n");
            }
            wypluwara.write("Wartosci zmiennych: \n");
            for (Zmienna zmienna : obceny.zmienne.getVector()){
                wypluwara.write(zmienna.getNazwa() + " = " + zmienna.getWartosc() +"\n");
            }
            wypluwara.close();
        } catch (Exception e){
            System.out.println("Blad zapisu do pliku");
            e.printStackTrace();
        }
    }

    public void uruchom(){
        String input;
        Scanner wciagara = new Scanner(System.in);
        int parametr;
        while (true){
            input = wciagara.next();
            switch (input) {
                case "s" -> {
                    parametr = wciagara.nextInt();
                    step(parametr);
                }
                case "c" -> {
                    conti();
                }
                case "e" -> {
                    return;
                }
                case "d" -> {
                    parametr = wciagara.nextInt();
                    display(parametr);
                }
                case "m" -> {
                    String sciezka = wciagara.next();
                    dump(sciezka);
                }
            }
        }
    }
}
