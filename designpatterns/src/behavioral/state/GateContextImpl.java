package behavioral.state;

public class GateContextImpl implements GateContext{

    private GateState currentState;

    public GateContextImpl(GateState initial){
        currentState = initial;
    }

    @Override
    public void enterGate() {
        currentState.userEnters(this);
    }

    @Override
    public void swipeCardSuccess() {
        currentState.paymentSuccess(this);
    }

    @Override
    public void swipeCardFailed() {
        currentState.paymentFailed(this);
    }

    @Override
    public void setState(GateState state) {
        currentState = state;
    }

    @Override
    public void currentState() {
        System.out.println(this.currentState);
    }
}