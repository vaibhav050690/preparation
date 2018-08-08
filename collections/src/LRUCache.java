import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

class Entry {
    int key;
    int value;
    Entry next;
    Entry prev;

    public Entry(int key, int value){
        this.key = key;
        this.value = value;

    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }
}

public class LRUCache {

    private HashMap<Integer,Entry> map;
    Entry head;
    Entry tail;
    int capacity;

    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        head = null;
        tail = null;

    }

    public void setHead(Entry entry){
        entry.prev = null;
        entry.next = head;

        if(head == null){
            head = entry;
            tail = entry;
        }
        else {
            head.prev = entry;
            head = entry;
        }
    }

    public int get(int key){
        if(map.containsKey(key)){
            Entry entry = map.get(key);
            removeEntry(entry);
            setHead(entry);
            return entry.value;
        }
        else {
            return -1;
        }
    }

    public void removeEntry(Entry entry){
        if(entry.prev == null){
            head = head.next;
        }
        else {
            entry.prev.next = entry.next;
        }
        if(entry.next == null){
            tail = entry.prev;
        }
        else {
            entry.next.prev = entry.prev;
        }
    }

    public void set(int key, int value){
        if(map.containsKey(key)){
            Entry oldEntry = map.get(key);
            oldEntry.value = value;
            removeEntry(oldEntry);
            setHead(oldEntry);
        }
        else {
            Entry newEntry = new Entry(key,value);
            if(map.size() < capacity){
                setHead(newEntry);
            }
            else {
                map.remove(tail.key);
                removeEntry(tail);
                setHead(newEntry);
            }
            map.put(key,newEntry);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        /*lruCache.set(1,1);
        lruCache.set(2,1);
        lruCache.set(3,1);
        lruCache.set(4,1);
        lruCache.set(5,1);
*/
    }


}