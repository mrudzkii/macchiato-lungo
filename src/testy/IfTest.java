import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfTest {

    @Test
    void testujPrawda(){
        Program main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(33))
                .dodajIf(Zmienna.oNazwie('x'), "<", Literal.oWartosci(2137), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(7312)).build())
                .dodajIf(Zmienna.oNazwie('x'), "<=", Literal.oWartosci(2137), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(7312)).build())
                .dodajIf(Zmienna.oNazwie('x'), "<>", Literal.oWartosci(2137), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(7312)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(7312, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testujFalsz(){
        Program main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(33))
                .dodajIf(Zmienna.oNazwie('x'), ">=", Literal.oWartosci(2137), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(7312)).build())
                .dodajIf(Zmienna.oNazwie('x'), "=", Literal.oWartosci(2137), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(7312)).build())
                .dodajIf(Zmienna.oNazwie('x'), ">", Literal.oWartosci(2137), new BlokBuilder()
                    .przypiszWartosc('x', Literal.oWartosci(7312)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(33, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
