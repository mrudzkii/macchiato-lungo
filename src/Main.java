public class Main {
    public static void main(String[] args) {
        Program main = new Program();
        new Blok(main);
            new DeklaracjaZmiennej(main, 'n', new Literal(30));
            new For(main, 'k', new Odejmowanie('n', new Literal(1)));
                new Blok(main);
                    new DeklaracjaZmiennej(main, 'p', new Literal(1));
                    new PrzypiszWartosc(main,'k', new Dodawanie('k', new Literal(2)));
                    new For(main, 'i', new Odejmowanie('k', new Literal(2)));
                        new PrzypiszWartosc(main,'i', new Dodawanie('i', new Literal(2)));
                        new If(main, new Modulo('k', 'i'), "=", new Literal(0));
                        new PrzypiszWartosc(main,'p', new Literal(0));
                        new EndIf(main);
                    new ZakonczFor(main);
                    new If(main, 'p', "=", new Literal(1));
                        new WypiszWartosc(main, 'k');
                    new EndIf(main);
                new ZakonczBlok(main);
            new ZakonczFor(main);
        new ZakonczBlok(main);
        main.uruchomBezDebugowania();

        Program test1 = new Program();
        new Blok(test1);
        new For(test1, 'i', new Literal(10));
        new If(test1, new Modulo('i', new Literal(2)), "=", new Literal(0));
        new WypiszWartosc(test1, 'i');
        new Else(test1);
        new EndIf(test1);
        new ZakonczFor(test1);
        new ZakonczBlok(test1);
        test1.uruchomBezDebugowania();
//        test1.uruchomZOdpluskiwaniem();

        Program testFora = new Program();
        new Blok(testFora);
        new For(testFora, 'i', new Literal(10));
        new WypiszWartosc(testFora, 'i');
        new ZakonczFor(testFora);
        new ZakonczBlok(testFora);
        testFora.uruchomBezDebugowania();
//        testFora.uruchomZOdpluskiwaniem();

        Program testIfa = new Program();
        new Blok(testIfa);
            new DeklaracjaZmiennej(testIfa, 'k', new Literal(2137));
            new For(testIfa, 'i', new Literal(10));
                new If(testIfa, new Modulo('i', new Literal(2)), "=", new Literal(0));
                    new WypiszWartosc(testIfa, 'i');
                new Else(testIfa);
                    new WypiszWartosc(testIfa, 'k');
                new EndIf(testIfa);
            new ZakonczFor(testIfa);
        new ZakonczBlok(testIfa);
        testIfa.uruchomBezDebugowania();
//        testIfa.uruchomZOdpluskiwaniem();
    }
}