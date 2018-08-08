package behavioral.chainofresponsibilty;

public class Rs1000Dispenser implements DispenserChain {

    DispenserChain next;

    @Override
    public void setChain(DispenserChain chain) {
        this.next = chain;
    }
    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        int num = amount/1000;
        int rem = amount%1000;
        if(num > 0){
            System.out.println(num + " Rs.1000 notes dispensed");
            if(rem != 0){
                this.next.dispense(new Currency(rem));
            }
        }
        else {
            this.next.dispense(currency);
        }
    }
}