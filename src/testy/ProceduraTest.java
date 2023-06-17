import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProceduraTest {
    private Program main;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void testuj(){

        main = new ProgramBuilder().blokZewnetrzny(new BlokBuilder()
                        .zadeklarujProcedure("kocham", List.of('p', 'i', 'w', 'o'), new BlokBuilder()
                                .przypiszWartosc('p', Literal.oWartosci(2))
                                .przypiszWartosc('i', Literal.oWartosci(1))
                                .przypiszWartosc('w', Literal.oWartosci(3))
                                .przypiszWartosc('o', Literal.oWartosci(7))
                                .wypiszWartosc(Zmienna.oNazwie('p'))
                                .wypiszWartosc(Zmienna.oNazwie('i'))
                                .wypiszWartosc(Zmienna.oNazwie('w'))
                                .wypiszWartosc(Zmienna.oNazwie('o'))
                                .build())
                        .wywolajProcedure("kocham", List.of(Literal.oWartosci(6), Literal.oWartosci(9), Literal.oWartosci(6), Literal.oWartosci(9)))
                ).build();
        main.uruchomBezDebugowania();
        try{
            assertEquals(true, main.getZewnetrzny().procedury.czyIstnieje("kocham"));
            assertEquals(false, main.getZewnetrzny().procedury.czyIstnieje("piwo"));
            assertEquals("2\n1\n3\n7", outputStreamCaptor.toString().trim());
//            assertEquals(2, main.getZewnetrzny().procedury.procedury.elementAt(0).getZmienne().getVector().elementAt(0).wylicz(main.getZewnetrzny().zmienne));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
