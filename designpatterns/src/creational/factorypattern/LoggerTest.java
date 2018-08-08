package creational.factorypattern;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class LoggerTest {

    public static void main(String[] args) {
        String type = "F";
        Logger logger = LoggerFactory.getLogger(type);
        logger.print();

    }
}
