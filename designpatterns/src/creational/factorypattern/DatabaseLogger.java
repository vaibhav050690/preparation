package creational.factorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class DatabaseLogger implements Logger {
    @Override
    public void print() {
        System.out.print("Database Logging");
    }
}
