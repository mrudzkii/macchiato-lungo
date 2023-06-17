import java.util.Vector;

public class Zmienne {
    private Vector<Zmienna> zmienne;

    public Vector<Zmienna> getVector() {
        return zmienne;
    }

    public Zmienne(){
        zmienne = new Vector<>();
    }

    public void dodajZmienna(Zmienna zmienna){
        zmienne.add(zmienna);
    }

    public void setZmienne(Vector<Zmienna> zmienne) {
        this.zmienne = zmienne;
    }

    public boolean czyZawiera(Zmienna zmienna){
        return zmienne.contains(zmienna);
    }

    public void dodajZmienna (char nazwa, int wartosc) throws PodwojnaDekleracjaExcepion {
        for(Zmienna zmienna : zmienne){
            if(zmienna.getNazwa() == nazwa) throw new PodwojnaDekleracjaExcepion("Podwojna deklaracja zmiennej " + nazwa);
        }
        zmienne.add(new Zmienna(nazwa, wartosc));
    }

    // Wypisuje wartosci wszystkich zmiennych
    public void wypiszWartosci(){
        for (Zmienna zmienna : zmienne){
            System.out.println(zmienna.getNazwa() + " = " + zmienna.getWartosc());
        }
    }

    public Zmienna ostatnioDodana(){
        return zmienne.lastElement();
    }

    // Wypisuje wartosc zmiennej o podanej nazwie (o ile istnieje)
    public void wypiszWartosc(char nazwa) throws BrakZmiennejException{
        for(Zmienna zmienna : zmienne){
            if(zmienna.getNazwa() == nazwa){
                System.out.println(zmienna.getWartosc());
                return;
            }
        }
        throw new BrakZmiennejException("Nie znaleziono zmiennej '" + nazwa + "' do wypisania");
    }

    public boolean czyZawiera(char nazwa){
        for (Zmienna z : zmienne){
            if(z.getNazwa() == nazwa)
                return true;
        }
        return false;
    }

    public void usunZmienna(char nazwa){
        for (Zmienna z : zmienne){
            if(z.getNazwa() == nazwa) {
                zmienne.remove(z);
                return;
            }
        }
    }
    protected int wartosc(char nazwa) throws BrakZmiennejException {
        for(Zmienna z : zmienne){
            if(z.getNazwa() == nazwa) return z.getWartosc();
        }
        throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa);
    }

    public void przypiszWartosc(char nazwa, int wartosc) throws BrakZmiennejException{
        for(Zmienna z : zmienne){
            if(z.getNazwa() == nazwa){
                z.ustawWartosc(wartosc);
                return;
            }
        }
        throw new BrakZmiennejException("Nie znaleziono zmiennej " + nazwa + " do przypisania wartosci");
    }

    public int liczbaZmiennych(){
        return zmienne.size();
    }
}
