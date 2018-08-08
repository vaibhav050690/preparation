package structural.flyweight;

public class CounterTerrorist implements Player {
    //intrinsic state
    private String task;
    //extrinsic state
    private String weapon;

    public CounterTerrorist(){
        this.task = "DEFUSE BOMB";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("TASK:" + this.task + "  WEAPON:" + this.weapon);
    }
}