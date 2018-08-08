package structural.flyweight;

import java.util.Random;

public class CounterStrike {

    static String[] playerType = {"Terrorist","CounterTerrorist"};
    static String[] weapons = {"Sniper","Gun","Knife","Machine Gun","Riffle","Ak-47"};


    public static String getRandomPlayerType(){
        Random random = new Random();
        return playerType[random.nextInt(playerType.length)];
    }

    public static String getRandomWeapon(){
        Random random = new Random();
        return weapons[random.nextInt(weapons.length)];
    }


    public static void main(String[] args) {
        for(int i =0; i<10; i++){
            Player p = PlayerFactory.getPlayer(getRandomPlayerType());
            p.assignWeapon(getRandomWeapon());
            p.mission();
        }
    }
}