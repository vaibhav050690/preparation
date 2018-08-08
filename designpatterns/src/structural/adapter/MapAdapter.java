package structural.adapter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapAdapter extends AbstractMap {

    private Map map;

    public MapAdapter(Object[][] array) {
        super();
        map = new HashMap();
        for(Object[] mapping : array){
            map.put(mapping[0], mapping[1]);
        }
    }

    @Override
    public Set<Entry> entrySet() {
        return map.entrySet();
    }

    public static void main(String[] args) {
        Integer[][] squares = { {2, 4}, {3, 9}, {4, 16}};
        MapAdapter mapAdapter = new MapAdapter(squares);
        System.out.println(mapAdapter);
    }
}