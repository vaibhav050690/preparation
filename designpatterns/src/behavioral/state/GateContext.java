package behavioral.state;

// Context interface which client uses to perform action
public interface GateContext {

    void enterGate();

    void swipeCardSuccess();

    void swipeCardFailed();

    void setState(GateState state);

    void currentState();
}