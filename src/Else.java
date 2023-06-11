import java.util.Vector;

public class Else extends Instrukcja{
    private Blok poprzedni;
    private If nowy;
    public Else(Program program){
        poprzedni = program.getBloki().lastElement();
        glebokosc = poprzedni.glebokosc;
        new EndIf(program);
        if(poprzedni.wyr1 != null && poprzedni.wyr2 != null){
            switch (poprzedni.operator){
                case "=":
                    nowy = new If(program, poprzedni.wyr1, "<>", poprzedni.wyr2);
                    break;
                case "<>":
                    nowy = new If(program, poprzedni.wyr1, "=", poprzedni.wyr2);
                    break;
                case "<":
                    nowy = new If(program, poprzedni.wyr1, ">=", poprzedni.wyr2);
                    break;
                case ">":
                    nowy = new If(program, poprzedni.wyr1, "<=", poprzedni.wyr2);
                    break;
                case "<=":
                    nowy = new If(program, poprzedni.wyr1, ">", poprzedni.wyr2);
                    break;
                case ">=":
                    nowy = new If(program, poprzedni.wyr1, "<", poprzedni.wyr2);
                    break;
            }
        }
        else if(poprzedni.wyr1 != null && poprzedni.wyr2 == null){
            switch (poprzedni.operator){
                case "=":
                    nowy = new If(program, poprzedni.wyr1, "<>", poprzedni.zmienna2);
                    break;
                case "<>":
                    nowy = new If(program, poprzedni.wyr1, "=", poprzedni.zmienna2);
                    break;
                case "<":
                    nowy = new If(program, poprzedni.wyr1, ">=", poprzedni.zmienna2);
                    break;
                case ">":
                    nowy = new If(program, poprzedni.wyr1, "<=", poprzedni.zmienna2);
                    break;
                case "<=":
                    nowy = new If(program, poprzedni.wyr1, ">", poprzedni.zmienna2);
                    break;
                case ">=":
                    nowy = new If(program, poprzedni.wyr1, "<", poprzedni.zmienna2);
                    break;
            }
        }else if(poprzedni.wyr1 == null && poprzedni.wyr2 == null){
            switch (poprzedni.operator){
                case "=":
                    nowy = new If(program, poprzedni.zmienna1, "<>", poprzedni.zmienna2);
                    break;
                case "<>":
                    nowy = new If(program, poprzedni.zmienna1, "=", poprzedni.zmienna2);
                    break;
                case "<":
                    nowy = new If(program, poprzedni.zmienna1, ">=", poprzedni.zmienna2);
                    break;
                case ">":
                    nowy = new If(program, poprzedni.zmienna1, "<=", poprzedni.zmienna2);
                    break;
                case "<=":
                    nowy = new If(program, poprzedni.zmienna1, ">", poprzedni.zmienna2);
                    break;
                case ">=":
                    nowy = new If(program, poprzedni.zmienna1, "<", poprzedni.zmienna2);
                    break;
            }
        }else if(poprzedni.wyr1 == null && poprzedni.wyr2 != null){
            switch (poprzedni.operator){
                case "=":
                    nowy = new If(program, poprzedni.zmienna1, "<>", poprzedni.wyr2);
                    break;
                case "<>":
                    nowy = new If(program, poprzedni.zmienna1, "=", poprzedni.wyr2);
                    break;
                case "<":
                    nowy = new If(program, poprzedni.zmienna1, ">=", poprzedni.wyr2);
                    break;
                case ">":
                    nowy = new If(program, poprzedni.zmienna1, "<=", poprzedni.wyr2);
                    break;
                case "<=":
                    nowy = new If(program, poprzedni.zmienna1, ">", poprzedni.wyr2);
                    break;
                case ">=":
                    nowy = new If(program, poprzedni.zmienna1, "<", poprzedni.wyr2);
                    break;
            }
        }
    }

    @Override
    protected boolean wykonaj(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return nowy.wykonaj(zmienne);
    }

    @Override
    protected boolean uruchom(Vector<Zmienna> zmienne) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return wykonaj(zmienne);
    }

    protected int step(Vector<Zmienna> zmienneUseless) throws BrakZmiennejException, DzieleniePrzezZeroException, PodwojnaDekleracjaExcepion {
        return nowy.step(zmienneUseless);
    }
}