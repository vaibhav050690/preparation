package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {

    private static Map<String,Player> map = new HashMap<>();

    public static Player getPlayer(String type){
        Player p = map.get(type);
        if(p == null){
            switch (type){
                case "Terrorist":
                    System.out.println("Player Terrorist Created");
                    p = new Terrorist();
                    break;
                case "CounterTerrorist":
                    System.out.println("Player CounterTerrorist Created");
                    p = new CounterTerrorist();
                    break;
                default:
                    throw new IllegalArgumentException("new player type");
            }
            map.put(type,p);
        }
        return p;
    }
}