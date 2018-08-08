import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    private int maxsize;

    public LRUCacheLinkedHashMap(int size){
        super(size,.75f,true);
        maxsize = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > maxsize;
    }

    public static void main(String[] args) {
        LRUCacheLinkedHashMap<String,String> lruCacheLinkedHashMap = new LRUCacheLinkedHashMap<>(3);
        lruCacheLinkedHashMap.put("1","1");
        lruCacheLinkedHashMap.put("2","1");
        lruCacheLinkedHashMap.put("3","1");
        lruCacheLinkedHashMap.get("1");
        //lruCacheLinkedHashMap.put("4","1");

        //lruCacheLinkedHashMap.put("5","1");

        System.out.println(lruCacheLinkedHashMap);

    }
}