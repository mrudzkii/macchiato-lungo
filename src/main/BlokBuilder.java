import java.util.List;
import java.util.Vector;

public class BlokBuilder {
    protected Vector<Instrukcja> instrukcje;
    protected Vector<Pair<Character, Wyrazenie>> zadeklarowaneZmienne;
    protected Procedury procedury;
    protected Vector<Blok> zadeklarowaneBloki;
    private static int glebokosc = 0;
//    protected int ktoraInstrukcja;

    public BlokBuilder(){
        glebokosc++;
        this.instrukcje = new Vector<>();
        this.zadeklarowaneZmienne = new Vector<>();
        this.procedury = new Procedury();
        this.zadeklarowaneBloki = new Vector<>();
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

    public BlokBuilder wypiszWartosc(Wyrazenie wyrazenie){
        instrukcje.add(new WypiszWartosc(wyrazenie));
        return this;
    }

    public BlokBuilder blok(Blok blok){
        blok.setProcedury(procedury);
        blok.setGlebokosc(glebokosc);
        zadeklarowaneBloki.add(blok);
        instrukcje.add(blok);
        return this;
    }

    public BlokBuilder dodajFor(char zmienna, Wyrazenie wyrazenie, Blok blok){
        instrukcje.add(new For(zmienna, wyrazenie, blok));
        return this;
    }

    public BlokBuilder dodajIf(Wyrazenie wyr1, String operator, Wyrazenie wyr2, Blok blok){
        instrukcje.add(new If(wyr1, operator, wyr2, blok));
        return this;
    }

    public BlokBuilder dodajElseIf(Wyrazenie wyr1, String operator, Wyrazenie wyr2, Blok blok, Blok blokElse){
        instrukcje.add(new IfElse(wyr1, operator, wyr2, blok, blokElse));
        return this;
    }

    public BlokBuilder d00pa(){
        instrukcje.add(new D00pa());
        return this;
    }


    public BlokBuilder zadeklarujProcedure(String nazwa, List<Character> parametry, Blok blok){
        procedury.dodajProcedure(new Procedura(nazwa, parametry, blok));
        return this;
    }

    public BlokBuilder wywolajProcedure(String nazwa, List<Wyrazenie> wartosci){
        instrukcje.add(new DeklaracjaWykonaniaProcedury(nazwa, wartosci));
        return this;
    }

    public Blok build(){
        glebokosc--;
        Blok res = new Blok(instrukcje, zadeklarowaneZmienne);
        res.setProcedury(procedury);
        for(Blok blok : zadeklarowaneBloki){
            blok.setPoprzedniBlok(res);
        }
        return res;
    }

}
