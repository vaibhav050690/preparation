
//http://javabypatel.blogspot.in/2016/09/concurrenthashmap-interview-questions.html
//https://dzone.com/articles/how-concurrenthashmap-works-internally-in-java





/*
The main reason that nulls aren't allowed in ConcurrentMaps (ConcurrentHashMaps, ConcurrentSkipListMaps)
is that ambiguities that may be just barely tolerable in non-concurrent maps can't be accommodated.
The main one is that if map.get(key) returns null, you can't detect whether the key explicitly maps to
null vs the key isn't mapped. In a non-concurrent map, you can check this via  map.contains(key), but in
a concurrent one, the map might have changed between calls.


*/

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapInternals {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put(null,"1");
        map.put("1",null);
        map.put("2",null);


        System.out.println(map);

    }


}