package creational.factorypattern;

import java.text.NumberFormat;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class FileLogger implements Logger {
    @Override
    public void print() {
        System.out.print("File Logging");
    }
}
