public class For extends Instrukcja{
    private char zmienna;
    private Wyrazenie wyrazenie;
    private Zmienna iterator;
    private int ileRazy;
    private boolean czyZainicjalizowano;
    private int ktoryObrot;

    private Blok blok;

    public For(char zmienna, Wyrazenie wyrazenie, Blok blok){
        super();
        this.nazwaInstrukcji = "For";
        this.zmienna = zmienna;
        this.wyrazenie = wyrazenie;
        this.ktoryObrot = 0;
        this.blok = blok;
        this.czyZainicjalizowano = false;
    }

    @Override
    public String getNazwaInstrukcji() {
        if(!czyZainicjalizowano){
            return nazwaInstrukcji;
        }
        return blok.getNazwaInstrukcji();
    }

    @Override
    protected boolean uruchom(Zmienne zmiennePoprzednie, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion,
            BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException{
        zmienne = new Zmienne();
        int wartosc = wyrazenie.wylicz(zmiennePoprzednie);
        ileRazy = wyrazenie.wylicz(zmiennePoprzednie);
        for (Zmienna zmienna : zmiennePoprzednie.getVector()){
            if (zmienna.getNazwa() == this.zmienna){
                iterator = new Zmienna(this.zmienna, wartosc);
                zmienne.dodajZmienna(iterator);
            }else if(!zmienne.czyZawiera(zmienna.getNazwa())) {
                zmienne.dodajZmienna(zmienna);
            }
        }
        if(!zmienne.czyZawiera(this.zmienna)){
            iterator = new Zmienna(this.zmienna, wartosc);
            zmienne.dodajZmienna(iterator);
        }
        for (int i = 0; i < ileRazy; i++) {
            iterator.ustawWartosc(i);
            blok.wykonaj(zmienne, procedury);
        }
//        iterator.ustawWartosc(0);
        return true;
    }

    @Override
    protected boolean wykonaj(Zmienne zmienne, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException {
        return true;
    }

    @Override
    protected int step(Zmienne zmiennePoprzednie, Procedury procedury) throws BrakZmiennejException, DzieleniePrzezZeroException,
            PodwojnaDekleracjaExcepion, BrakProceduryException, ZlaLiczbaParametrowException, PodwojnaDeklaracjaProceduryException {
        if(!czyZainicjalizowano){
            zmienne = new Zmienne();
            int wartosc = wyrazenie.wylicz(zmiennePoprzednie);
            ileRazy = wyrazenie.wylicz(zmiennePoprzednie);
            for (Zmienna zmienna : zmiennePoprzednie.getVector()){
                if (zmienna.getNazwa() == this.zmienna){
                    iterator = new Zmienna(this.zmienna, wartosc);
                    iterator.ustawWartosc(0);
                    zmienne.dodajZmienna(iterator);
                }else if(!zmienne.czyZawiera(zmienna.getNazwa())){
                    zmienne.dodajZmienna(zmienna);
                }
            }
            if(!zmienne.czyZawiera(this.zmienna)){
                iterator = new Zmienna(this.zmienna, wartosc);
                zmienne.dodajZmienna(iterator);
            }
            czyZainicjalizowano = true;
            return 0;
        }
        if(ktoryObrot < ileRazy) {
            int wykonano = blok.step(zmienne, procedury);
            if(wykonano == 1 && blok.ktoraInstrukcja == blok.getInstrukcje().size()) {
                ktoryObrot++;
                iterator.ustawWartosc(ktoryObrot);
            }
            if(ktoryObrot == ileRazy) {
                ktoryObrot = 0;
                czyZainicjalizowano = false;
                iterator.ustawWartosc(0);
                return 1;
            }
            else{
                return 0;
            }
        }
        else return 1;
    }
}
