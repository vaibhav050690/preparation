package behavioral.template;

public class IPhoneCompiler extends CrossCompiler {
    @Override
    protected void collectSource() {
        System.out.println("Iphone:collectSource");
    }

    @Override
    protected void compileToTarget() {
        System.out.println("Iphone:compileToTarget");
    }
}