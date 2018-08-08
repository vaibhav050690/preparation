package behavioral.chainofresponsibilty;

public class Rs100Dispenser implements DispenserChain {

    DispenserChain next;


    @Override
    public void setChain(DispenserChain chain) {
        this.next = chain;
    }

    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        int num = amount/100;
        int rem = amount%100;
        if(num > 0){
            System.out.println(num + " Rs.100 notes dispensed");
            if(rem != 0){
                this.next.dispense(new Currency(rem));
            }
        }
        else {
            this.next.dispense(currency);
        }
    }
}