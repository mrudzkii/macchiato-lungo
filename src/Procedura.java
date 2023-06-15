import java.util.List;
import java.util.Vector;

public class Procedura extends Instrukcja{
    private String nazwa;
    private List<Character> argumentyNazwy;
    private List<Wyrazenie> argumentyWyrazenia;
    private Zmienne zmienne;
    private Blok blok;

    public String getNazwa() {
        return nazwa;
    }

    public Procedura(String nazwa, List<Character> argumentyNazwy, Blok blok) {
        this.nazwa = nazwa;
        this.argumentyNazwy = argumentyNazwy;
        this.blok = blok;
    }

    public void dodajWartosci(List<Wyrazenie> argumentyWyrazenia){
        this.argumentyWyrazenia = argumentyWyrazenia;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienneWczesniejsze, Procedury procedury) throws  BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        zmienne = zmienneWczesniejsze;
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
    protected boolean uruchom(Zmienne zmienneWczesniejsze, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        zmienne = zmienneWczesniejsze;
//        if(procedury.czyIstnieje(this.getNazwa())) procedury.procedury.remove(procedury.znajdzProcedure(this.nazwa));
        for (int i = 0; i < argumentyNazwy.size(); i++) {
            int wartosc = argumentyWyrazenia.get(i).wylicz(zmienne);
            if (zmienne.czyZawiera(argumentyNazwy.get(i))) {
                zmienne.usunZmienna(argumentyNazwy.get(i));
            }
            zmienne.dodajZmienna(argumentyNazwy.get(i), wartosc);
        }
        return blok.wykonaj(zmienne, procedury);
    }
}
