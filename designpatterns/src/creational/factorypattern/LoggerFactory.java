package creational.factorypattern;

/**
 * Created by vaibhavmishra on 20/9/17.
 */
public class LoggerFactory {

    public static Logger getLogger(String type){
        if(type.equalsIgnoreCase("F")){
            return new FileLogger();
        }
        if(type.equalsIgnoreCase("D")){
            return new DatabaseLogger();
        }
        if(type.equalsIgnoreCase("C")){
            return new ConsoleLogger();
        }
        return null;
    }
}
