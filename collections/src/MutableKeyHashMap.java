import java.util.HashMap;
import java.util.Objects;

class Emp {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emp)) return false;
        Emp emp = (Emp) o;
        return id == emp.id &&
                Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;
}


public class MutableKeyHashMap
{

    public static void main(String[] args) {
        Emp emp = new Emp(1,"a");
        Emp emp1 = new Emp(2,"b");
        Emp emp2 = new Emp(3,"c");
        Emp emp3 = new Emp(4,"d");
        HashMap<Emp,Integer> map = new HashMap<>();
        map.put(emp,1);
        map.put(emp1,2);
        map.put(emp2,3);
        map.put(emp3,4);
        System.out.println(map.get(emp));
        emp.setName("b");
        System.out.println(map.get(emp));

    }
}