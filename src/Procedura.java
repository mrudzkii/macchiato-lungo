import java.util.List;
import java.util.Vector;

public class Procedura extends Instrukcja{
    private String nazwa;
    private List<Character> argumentyNazwy;
    private List<Wyrazenie> argumentyWyrazenia;
    private Zmienne zmienne;
    private Blok blok;
    private boolean czyZainicjalizowano;

    public String getNazwa() {
        return nazwa;
    }

    public List<Character> getNazwyLista(){
        return argumentyNazwy;
    }

    public Procedura(String nazwa, List<Character> argumentyNazwy, Blok blok) {
        this.nazwa = nazwa;
        this.argumentyNazwy = argumentyNazwy;
        this.blok = blok;
        czyZainicjalizowano = false;
        this.nazwaInstrukcji = "Procedura " + nazwa;
        this.zmienne = new Zmienne();
    }

    public void dodajWartosci(List<Wyrazenie> argumentyWyrazenia){
        this.argumentyWyrazenia = argumentyWyrazenia;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienneWczesniejsze, Procedury procedury) throws  BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        zmienne = new Zmienne();
        for(Zmienna zmienna : zmienneWczesniejsze.getVector())
            zmienne.dodajZmienna(zmienna);
        if(argumentyNazwy.size() != argumentyWyrazenia.size())
            throw new ZlaLiczbaParametrowException("Zla liczba parametrów procedury '" + nazwa + "' \nZadeklarowano: " +
                    argumentyNazwy.size() + "\nPrzyjęto: " + argumentyWyrazenia.size());
        for (int i = 0; i < argumentyNazwy.size(); i++) {
            int wartosc = argumentyWyrazenia.get(i).wylicz(zmienne);
            char nazwa = argumentyNazwy.get(i);
            if (zmienne.czyZawiera(nazwa)) {
                zmienne.usunZmienna(nazwa);
            }
            zmienne.dodajZmienna(argumentyNazwy.get(i), wartosc);
        }
        // Zeruję w bloku jego zaddeklarowane i isniejace zmienne - traktuje go w pewnym sensie jako nowy blok,
        // tyle, że z tymi samymi instrukcjami.
        blok.setZadeklarowaneZmienne(new Vector<>());
        blok.setZmienne(new Zmienne());
        return blok.wykonaj(zmienne, procedury);
    }

    @Override
    protected int step(Zmienne zmienneWczesniejsze, Procedury wczesniejszeProcedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        if(!czyZainicjalizowano) {
            zmienne = new Zmienne();
            for(Zmienna zmienna : zmienneWczesniejsze.getVector())
                zmienne.dodajZmienna(zmienna);
            for (int i = 0; i < argumentyNazwy.size(); i++) {
                int wartosc = argumentyWyrazenia.get(i).wylicz(zmienne);
                if (zmienne.czyZawiera(argumentyNazwy.get(i))) {
                    zmienne.usunZmienna(argumentyNazwy.get(i));
                }
                zmienne.dodajZmienna(argumentyNazwy.get(i), wartosc);
                czyZainicjalizowano = true;
            }
            blok.setZadeklarowaneZmienne(new Vector<>());
            blok.setZmienne(new Zmienne());
            return 0;
        }
        return blok.step(zmienne, wczesniejszeProcedury);
    }
}
