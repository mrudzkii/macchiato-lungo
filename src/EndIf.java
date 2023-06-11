public class EndIf extends ZakonczBlok{
    public EndIf(Program program) {
        super(program);
        glebokosc = program.getBloki().lastElement().glebokosc;
        this.nazwaInstrukcji = "EndIf";
    }
}
