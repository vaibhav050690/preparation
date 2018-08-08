package behavioral.state;

public class Client {

    public static void main(String[] args) {
        GateContext gate = new GateContextImpl(new GateClosedState());
        gate.currentState();
        System.out.println("Swipe card failed");
        gate.swipeCardFailed();
        gate.currentState();

        System.out.println("User tries to enter");
        gate.enterGate();
        gate.currentState();

        System.out.println("Swipe card success");
        gate.swipeCardSuccess();
        gate.currentState();

        System.out.println("user enters");
        gate.enterGate();
        gate.currentState();

        System.out.println("User tries to enter");
        gate.enterGate();
        gate.currentState();

    }
}