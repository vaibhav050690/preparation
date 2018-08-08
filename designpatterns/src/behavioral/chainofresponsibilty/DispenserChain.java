package behavioral.chainofresponsibilty;

public interface DispenserChain {

    void setChain(DispenserChain chain);

    void dispense(Currency amount);

}