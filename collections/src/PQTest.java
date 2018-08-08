import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class User {
    private String name;
    private int count;

    public User(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

public class PQTest {

    public static void main(String[] args) {
        PriorityQueue<User> pq = new PriorityQueue<>(5, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return t1.getCount() - user.getCount();
            }
        });
        Map<String,User> map = new HashMap<>();
        User u1 = new User("a",1);
        map.put("a",u1);
        User u2 = new User("b",1);
        map.put("b",u2);
        User u3 = new User("c",1);
        map.put("c",u3);
        pq.offer(u1);
        pq.offer(u2);
        pq.offer(u3);
        User u = map.get("c");
        u.setCount(2);
        pq.offer(u);
        System.out.println(pq);
    }
}