package comparableandcomparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

class MapTest {
    int id;
    String name;

    @Override
    public String toString() {
        return "MapTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public MapTest(int id, String name) {
        this.id = id;
        this.name = name;
    }


}

class MapTestComparator implements Comparator<MapTest>{

    @Override
    public int compare(MapTest mapTest, MapTest t1) {
        int compareId = mapTest.id - t1.id;
        int compareName = t1.name.compareTo(mapTest.name);
        if(compareId == 0){
            return compareName;
        }
        return compareId;
    }
}

public class TreeMapTest {
    public static void main(String[] args) {
        MapTest mapTest = new MapTest(1,"a");
        MapTest mapTest1 = new MapTest(1,"b");
        MapTest mapTest2 = new MapTest(2,"c");
        MapTest mapTest3 = new MapTest(1,"d");




        TreeMap<MapTest,String> map = new TreeMap<>(new MapTestComparator());
        map.put(mapTest,"1");
        map.put(mapTest1,"1");map.put(mapTest2,"1");map.put(mapTest3,"1");
        System.out.println(map);



    }
}