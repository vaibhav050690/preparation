package behavioral.command;

//concrete receiver

public class AirConditionerImpl implements AirConditioner {
    @Override
    public void start() {
        System.out.println("Air Conditioner is on.");
    }

    @Override
    public void stop() {
        System.out.println("Air Conditioner is off.");
    }
}