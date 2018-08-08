package behavioral.command;

public class StopCommand implements Command {

    private AirConditioner airConditioner;

    public StopCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        this.airConditioner.stop();
    }

    @Override
    public void undo() {
        this.airConditioner.start();
    }
}