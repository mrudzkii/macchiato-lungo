import java.util.List;

public class DeklaracjaWykonaniaProcedury extends Instrukcja{

    private String nazwa;
    private List<Wyrazenie> wartosciArgumentow;

    public DeklaracjaWykonaniaProcedury(String nazwa, List<Wyrazenie> wartosciArgumentow) {
        this.nazwa = nazwa;
        this.wartosciArgumentow = wartosciArgumentow;
        this.nazwaInstrukcji = "Procedura '" + nazwa + "'";
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        Procedura procedura = procedury.znajdzProcedure(nazwa);
        procedura.dodajWartosci(wartosciArgumentow);
        return procedura.wykonaj(zmienne, procedury);
    }

    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException{
        Procedura procedura = procedury.znajdzProcedure(nazwa);
        procedura.dodajWartosci(wartosciArgumentow);
        return procedura.wykonaj(zmienne, procedury);
//        return true;
    }
}
