package behavioral.state;

public class GateClosedState implements GateState {

    @Override
    public void paymentSuccess(GateContext gateContext) {
        gateContext.setState(new GateOpenState());
    }

    @Override
    public String toString() {
        return "GateClosedState{}";
    }

    @Override
    public void paymentFailed(GateContext gateContext) {
        //do nothing
    }

    @Override
    public void userEnters(GateContext gateContext) {
        //do nothing
    }
}