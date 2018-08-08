package behavioral.state;

public class GateOpenState implements GateState {

    @Override
    public void paymentSuccess(GateContext gateContext) {
        //do nothing
    }

    @Override
    public String toString() {
        return "GateOpenState{}";
    }

    @Override
    public void paymentFailed(GateContext gateContext) {
        //do nothing
    }

    @Override
    public void userEnters(GateContext gateContext) {
        gateContext.setState(new GateClosedState());
    }
}