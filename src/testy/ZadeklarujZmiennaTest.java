import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZadeklarujZmiennaTest {
    private Program main;

    @Test
    public void testuj(){
        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                .zadeklarujZmienna('x', Literal.oWartosci(21))
                .zadeklarujZmienna('y', Literal.oWartosci(37))).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(21, main.getZewnetrzny().getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
            assertEquals(37, main.getZewnetrzny().getZmienne().getVector().elementAt(1).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
