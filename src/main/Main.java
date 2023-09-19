import java.util.List;

public class Main {
    public static void main(String[] args) {

        var przyklad = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(101))
                .zadeklarujZmienna('y', Literal.oWartosci(1))
                .zadeklarujProcedure("out", List.of('a'), new BlokBuilder()
                        .wypiszWartosc(Dodawanie.wyrazen(Zmienna.oNazwie('a'), Zmienna.oNazwie('x'))).build())
                .przypiszWartosc('x', Odejmowanie.wyrazen(Zmienna.oNazwie('x'), Zmienna.oNazwie('y')))
                .wywolajProcedure("out", List.of(Zmienna.oNazwie('x')))
                .wywolajProcedure("out", List.of(Literal.oWartosci(100)))
                .blok(new BlokBuilder()
                        .zadeklarujZmienna('x', Literal.oWartosci(10))
                        .wywolajProcedure("out", List.of(Literal.oWartosci(100)))
                        .build())
        ).build();
//        przyklad.uruchomBezDebugowania();
        przyklad.uruchomZOdpluskiwaniem();
    }
}