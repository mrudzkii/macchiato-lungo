import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WyrazeniaTest {
    private Program main;

    @Test
    public void testuj(){
        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x',
                        Dodawanie.wyrazen(Dzielenie.wyrazen(Mnozenie.wyrazen(Odejmowanie.wyrazen(Literal.oWartosci(5), Literal.oWartosci(2)), Literal.oWartosci(7)), Modulo.wyrazen(Literal.oWartosci(7), Literal.oWartosci(4))), Literal.oWartosci(2130))))
                        .build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(2137, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
