package behavioral.chainofresponsibilty;

public class AtmDispenseChain {

    private DispenserChain c;

    public AtmDispenseChain(){
        /*
        we should create the chain carefully, otherwise a processor might not be getting any request at all.
        For example, in our implementation if we keep the first processor chain as Rs100Dispenser and then
        Rs500Dispenser, then the request will never be forwarded to the second processor and the chain will
        become useless.
         */
        c = new Rs1000Dispenser();
        DispenserChain c1 = new Rs500Dispenser();
        DispenserChain c2 = new Rs100Dispenser();
        c.setChain(c1);
        c1.setChain(c2);
    }


    public static void main(String[] args) {
        AtmDispenseChain atmDispenseChain = new AtmDispenseChain();
        atmDispenseChain.c.dispense(new Currency(700));
    }
}