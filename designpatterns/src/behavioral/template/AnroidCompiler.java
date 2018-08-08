package behavioral.template;

public class AnroidCompiler extends CrossCompiler {

    @Override
    protected void collectSource() {
        System.out.println("Android:collectSource");
    }

    @Override
    protected void compileToTarget() {
        System.out.println("Android:compileToTarget");
    }
}