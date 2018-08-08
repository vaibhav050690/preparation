package behavioral.template;

public abstract class CrossCompiler {

    public final void compile(){
        collectSource();
        compileToTarget();
    }

    protected abstract void collectSource();

    protected abstract void compileToTarget();


}