import java.util.Vector;

public class BlokBuilder {
    protected Vector<Instrukcja> instrukcje;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneWczesniejZmienne;
    protected Blok poprzedniBlok;
//    protected int ktoraInstrukcja;

    public BlokBuilder(){
        this.instrukcje = new Vector<>();
        this.zadeklarowaneZmienne = new Vector<>();
    }

    public BlokBuilder zadeklarujZmienna(char nazwa, Wyrazenie wartosc){
        zadeklarowaneZmienne.add(new Pair<>(nazwa, wartosc));
        return this;
    }

    public BlokBuilder przypiszWartosc(char zmienna, Wyrazenie wartosc){
        instrukcje.add(new PrzypiszWartosc(zmienna, wartosc));
        return this;
    }

    public BlokBuilder przypiszWartosc(char zmienna1, Zmienna zmienna2){
        instrukcje.add(new PrzypiszWartosc(zmienna1, zmienna2));
        return this;
    }

    public BlokBuilder wypiszWartosc(char zmienna){
        instrukcje.add(new WypiszWartosc(zmienna));
        return this;
    }

    public BlokBuilder blok(Blok blok){
        instrukcje.add(blok);
        return this;
    }

    public Blok build(){
        return new Blok(instrukcje, zadeklarowaneZmienne);
    }
}
