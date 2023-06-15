import java.util.Vector;

public class Procedury {
    protected Vector<Procedura> procedury;

    public Procedury() {
        this.procedury = new Vector<>();
    }

    public Vector<Procedura> getProcedury(){
        return procedury;
    }

    public boolean czyIstnieje(String nazwa) {
        for (Procedura p : procedury) {
            if (p.getNazwa() == nazwa)
                return true;
        }
        return false;
    }

    public void dodajProcedure(Procedura procedura){
        procedury.add(procedura);
    }

    public Procedura znajdzProcedure(String nazwa) throws BrakProceduryException{
        for (Procedura p : procedury){
            if(p.getNazwa() == nazwa)
                return p;
        }
        throw new BrakProceduryException("Nie znaleziono procedury o nazwie: " + nazwa);
    }
}