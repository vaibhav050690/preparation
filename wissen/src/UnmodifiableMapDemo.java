import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UnmodifiableMapDemo {


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","one");
        map.put("2","two");
        map = Collections.unmodifiableMap(map);
        System.out.println(map);
        map.put("3","three");

    }
}