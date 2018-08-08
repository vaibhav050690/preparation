package behavioral.command;

public class StartCommand implements Command {

    private AirConditioner airConditioner;

    public StartCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        this.airConditioner.start();
    }

    @Override
    public void undo() {
        this.airConditioner.stop();
    }
}