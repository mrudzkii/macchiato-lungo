import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForTest {
    @Test
    void testuj(){
        Program main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                        .zadeklarujZmienna('x', Literal.oWartosci(666))
                .dodajFor('i', Literal.oWartosci(43), new BlokBuilder()
                        .przypiszWartosc('x', Zmienna.oNazwie('i')).build()))
                .build();

        main.uruchomBezDebugowania();
        try{
            assertEquals(42, Zmienna.oNazwie('x').wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
