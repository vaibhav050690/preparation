package behavioral.command;

public class Client {

    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditionerImpl();
        Command start = new StartCommand(airConditioner);
        Command stop = new StopCommand(airConditioner);
        Switch sw = new Switch();
        System.out.println("Execute");
        sw.storeAndExecute(start);
        sw.storeAndExecute(stop);
        System.out.println("revoke");
        sw.revoke();
        sw.revoke();
    }
}