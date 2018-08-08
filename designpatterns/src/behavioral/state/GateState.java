package behavioral.state;

public interface GateState {

    //action for swipe card success
    void paymentSuccess(GateContext gateContext);

    //action for swipe card fails
    void paymentFailed(GateContext gateContext);

    //action for user entering the gate
    void userEnters(GateContext gateContext);
}