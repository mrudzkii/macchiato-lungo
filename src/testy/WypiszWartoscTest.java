import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WypiszWartoscTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void testWykonaj() {

        Program program = new ProgramBuilder()
                .blokZewnetrzny(new BlokBuilder()
                        .zadeklarujZmienna('x', Literal.oWartosci(420))
                        .wypiszWartosc(Zmienna.oNazwie('x'))
                ).build();
        program.uruchomBezDebugowania();
        assertEquals("420\nx = 420", outputStreamCaptor.toString().trim());
    }
}