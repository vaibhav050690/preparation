package behavioral.command;


import java.util.Stack;

//invoker
public class Switch {

    private Stack<Command> commandHistory = new Stack<>();

    public Switch(){

    }

    public void storeAndExecute(Command command){
        commandHistory.push(command);
        command.execute();
    }

    public void revoke(){
        if(!commandHistory.isEmpty()){
            commandHistory.pop().undo();
        }
    }
}