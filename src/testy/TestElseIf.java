import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElseIf {
    @Test
    void testujPrawda(){
        Program main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "=", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<", Literal.oWartosci(6669), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), ">", Literal.oWartosci(0), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), ">=", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<=", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<>", Literal.oWartosci(777), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(1, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testujFalsz(){
        Program main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<>", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), ">=", Literal.oWartosci(6669), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<=", Literal.oWartosci(0), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "<", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), ">", Literal.oWartosci(3721), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(3721))
                .dodajElseIf(Zmienna.oNazwie('x'), "=", Literal.oWartosci(777), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(1)).build(), new BlokBuilder()
                        .przypiszWartosc('x', Literal.oWartosci(96)).build())).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(96, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
