package creational.factorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class ConsoleLogger implements Logger {
    @Override
    public void print() {
        System.out.print("Console Logging");
    }
}
