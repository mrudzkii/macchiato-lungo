import java.util.Vector;

public class ProgramBuilder {
    private Vector<Blok> bloki;
    private Blok zewnetrzny;
    private BlokBuilder zewnetrznyBuilder;
    public ProgramBuilder(){
        this.bloki = new Vector<>();
    }

    public ProgramBuilder blokZewnetrzny(BlokBuilder zewnetrznyBuilder){
        this.zewnetrznyBuilder = zewnetrznyBuilder;
        return this;
    }

    public Program build(){
        return new Program(zewnetrznyBuilder.build());
    }
}
