//public class Main {
//    public static void main(String[] args) {
//        Program main = new Program();
//        new Blok(main);
//            new DeklaracjaZmiennej(main, 'n', Literal.oWartosci(30));
//            new For(main, 'k', Odejmowanie.wyrazen('n', Literal.oWartosci(1)));
//                new Blok(main);
//                    new DeklaracjaZmiennej(main, 'p', Literal.oWartosci(1));
//                    new PrzypiszWartosc(main,'k', Dodawanie.wyrazen('k', Literal.oWartosci(2)));
//                    new For(main, 'i',Odejmowanie.wyrazen('k', Literal.oWartosci(2)));
//                        new PrzypiszWartosc(main,'i', Dodawanie.wyrazen('i', Literal.oWartosci(2)));
//                        new If(main, Modulo.wyrazen('k', 'i'), "=", Literal.oWartosci(0));
//                        new PrzypiszWartosc(main,'p', Literal.oWartosci(0));
//                        new EndIf(main);
//                    new ZakonczFor(main);
//                    new If(main, 'p', "=", Literal.oWartosci(1));
//                        new WypiszWartosc(main, 'k');
//                    new EndIf(main);
//                new ZakonczBlok(main);
//            new ZakonczFor(main);
//        new ZakonczBlok(main);
//        main.uruchomBezDebugowania();
//
//        Program test1 = new Program();
//        new Blok(test1);
//        new For(test1, 'i', new Literal(10));
//        new If(test1, new Modulo('i', new Literal(2)), "=", new Literal(0));
//        new WypiszWartosc(test1, 'i');
//        new Else(test1);
//        new EndIf(test1);
//        new ZakonczFor(test1);
//        new ZakonczBlok(test1);
//        test1.uruchomBezDebugowania();
////        test1.uruchomZOdpluskiwaniem();
//
//        Program testFora = new Program();
//        new Blok(testFora);
//        new For(testFora, 'i', new Literal(10));
//        new WypiszWartosc(testFora, 'i');
//        new ZakonczFor(testFora);
//        new ZakonczBlok(testFora);
//        testFora.uruchomBezDebugowania();
////        testFora.uruchomZOdpluskiwaniem();
//
//        Program testIfa = new Program();
//        new Blok(testIfa);
//            new DeklaracjaZmiennej(testIfa, 'k', new Literal(2137));
//            new For(testIfa, 'i', new Literal(10));
//                new If(testIfa, new Modulo('i', new Literal(2)), "=", new Literal(0));
//                    new WypiszWartosc(testIfa, 'i');
//                new Else(testIfa);
//                    new WypiszWartosc(testIfa, 'k');
//                new EndIf(testIfa);
//            new ZakonczFor(testIfa);
//        new ZakonczBlok(testIfa);
//        testIfa.uruchomBezDebugowania();
////        testIfa.uruchomZOdpluskiwaniem();
//    }
//}

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        var program = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
//                .zadeklarujZmienna('x', Literal.oWartosci(21))
//                .zadeklarujZmienna('y', Odejmowanie.wyrazen(Dodawanie.wyrazen('x', Literal.oWartosci(20)), Literal.oWartosci(4)))
//                .wypiszWartosc(Zmienna.oNazwie('x'))
//                .wypiszWartosc(Zmienna.oNazwie('y'))
//                .blok(new BlokBuilder()
//                        .zadeklarujZmienna('x', Mnozenie.wyrazen(Zmienna.oNazwie('y'), Literal.oWartosci(3)))
//                        .zadeklarujZmienna('y', Literal.oWartosci(42))
//                        .wypiszWartosc(Zmienna.oNazwie('x'))
//                        .wypiszWartosc(Zmienna.oNazwie('y')).build())
//        ).build();
//        program.uruchomBezDebugowania();

        var sraka = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(101))
                .zadeklarujZmienna('y', Literal.oWartosci(1))
                .zadeklarujProcedure("out", List.of('a'), new BlokBuilder()
                        .wypiszWartosc(Dodawanie.wyrazen(Zmienna.oNazwie('a'), Zmienna.oNazwie('x'))).build())
                .przypiszWartosc('x', Odejmowanie.wyrazen(Zmienna.oNazwie('x'), Zmienna.oNazwie('y')))
//                .wypiszWartosc(Zmienna.oNazwie('x'))
                .wywolajProcedure("out", List.of(Zmienna.oNazwie('x')))
                .wywolajProcedure("out", List.of(Literal.oWartosci(100)))
//                .wywolajProcedure("out", List.of(Literal.oWartosci(21), Literal.oWartosci(37)))
//                .wywolajProcedure("blednaNazwa", List.of(Literal.oWartosci(69)))
                .blok(new BlokBuilder()
                        .zadeklarujZmienna('x', Literal.oWartosci(10))
                        .wywolajProcedure("out", List.of(Literal.oWartosci(100)))
                        .build())
        ).build();

        sraka.uruchomBezDebugowania();
    }
}