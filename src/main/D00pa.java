//Bonusowa instrukcja ulatwiajaca debugowanie w najbardziej przystepny sposob - wyswietlajac prosty komunikat na wyjscie
public class D00pa extends Instrukcja{
    public D00pa() {
        this.nazwaInstrukcji = "D00pa";
    }

    @Override
    protected boolean uruchom(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        System.out.println("d00pa");
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return true;
    }
}
