import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrzypiszWartoscTest {
    private Program main;

    @Test
    public void testuj(){
        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(21))
                .zadeklarujZmienna('y', Literal.oWartosci(37))
                .przypiszWartosc('x', Dodawanie.wyrazen(Zmienna.oNazwie('x'), Zmienna.oNazwie('y')))
                .przypiszWartosc('y', Odejmowanie.wyrazen(Zmienna.oNazwie('x'), Zmienna.oNazwie('y')))
                ).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(58, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
            assertEquals(21, main.getZewnetrzny().getZmienne().getVector().elementAt(1).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
