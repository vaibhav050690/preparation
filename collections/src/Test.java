import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.WeakHashMap;

interface Enumable {
    void methodA();
}

enum MyEnum implements Enumable  {
    INSTANCE, INSTANCE1("Ins");
    private int i = 0;
    MyEnum(String a){
        this.value = a;
    }
    MyEnum(){

    }

    public String getValue() {
        return this.value;
    }

    private String value;
    public void methodA(){
        System.out.println("A");
    }

}


class A {
    Integer i;

    public A(Integer i){
        this.i = i;
    }

    @Override
    public String toString() {
        return "A{" +
                "i=" + i +
                '}';
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(i, a.i);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(i);
    }*/
}

class Employee1Comaparator implements Comparator<Employee1>{

    @Override
    public int compare(Employee1 e1, Employee1 e2) {
        /*if("ceo".equalsIgnoreCase(e1.getDesignation()) || "ceo".equalsIgnoreCase(e2.getDesignation())){
            return -1;
        }*/
        if("ceo".equalsIgnoreCase(e1.getDesignation())){
            return -1;
        }
        if("ceo".equalsIgnoreCase(e2.getDesignation())){
            return 1;
        }

        if(e1.getId()-e2.getId() == 0){
            return e1.getDesignation().compareTo(e2.getDesignation());
        }
        return e1.getId()-e2.getId();
    }
}

class Employee1{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public String toString() {
        return "Employee1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String name;

    public Employee1(int id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }



    private String designation;



}
public class Test {

    public static void main(String[] args) {
        /*ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListIterator<Integer> iterator = list.listIterator();
        int i = 5;
        while (iterator.hasNext()){
            iterator.add(i);
            i++;
            System.out.println(iterator.next());
        }

        System.out.println(list);*/
     /*   LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(4);
        integers.add(5);
        linkedList.removeAll(integers);
        System.out.println(linkedList);
        Stack<Integer> stack = new Stack<>();
        stack.ensureCapacity(3);
        System.out.println(stack);*/
        /*LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap(2,.75f,true);
        linkedHashMap.put(1000,1);
        linkedHashMap.put(2000,2);
        linkedHashMap.put(3000,3);
        linkedHashMap.put(4000,4);
        linkedHashMap.put(5000,5);
        linkedHashMap.put(6000,6);
        linkedHashMap.put(7000,7);
        for(Integer i : linkedHashMap.values()){
            System.out.println(i);
        }
        linkedHashMap.get(1000);
        linkedHashMap.get(3000);
        for(Integer i : linkedHashMap.values()){
            System.out.println(i);
        }
        System.out.println("\n\n");
        for(Map.Entry entry : linkedHashMap.entrySet()){
            System.out.println(entry);
        }*/
        /*WeakHashMap<WeakReference<Integer>, Integer> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(new WeakReference<Integer>(1),1);
        weakHashMap.put(new WeakReference<Integer>(2),2);
        weakHashMap.put(new WeakReference<Integer>(3),3);
        weakHashMap.put(new WeakReference<Integer>(4),4);
        System.out.println(weakHashMap);
        System.gc();
        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {

        }
        System.out.println(weakHashMap);*/

       /* List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListIterator<Integer> listIterator = list.listIterator();
        listIterator.add(1);
    */  /*  while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
        System.out.println(list);
        Map<String,String> map = new HashMap<>();
        map.keySet().iterator();*/
/*
        HashMap<A,Integer> map = new HashMap();
        A a = new A(0);
        A b = new A(0);
        A c = new A(0);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        map.put(a,0);
        map.put(b,1);
        map.put(c,2);
        System.out.println(map);*/

        /*int a = 1;
        int b = 1;
        System.out.println(a==b);

        int i = 10;
        long l = i;


*/
     /*   MyEnum myEnum = MyEnum.INSTANCE;
        MyEnum myEnum1 = MyEnum.INSTANCE1;
        System.out.println(myEnum1.getValue());
        System.out.println(myEnum.getValue());
*/
       /* List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(0);
        list.add(2);
        list.add(30);
        list.add(9);
        list.add(7);
        list.add(null);
        Collections.sort(list);
        System.out.println(list);*/

        Set<Employee1> set = new TreeSet<Employee1>(new Employee1Comaparator());
        set.add(new Employee1(3,"batnu","developer"));
        set.add(new Employee1(10,"vaibhav","ceo"));
        set.add(new Employee1(5,"vaibhav","ceo"));
        set.add(new Employee1(1,"akhilesh","director"));
        set.add(new Employee1(-9,"prateek","qa"));
        set.add(new Employee1(1,"pranav","tester"));
        set.add(new Employee1(1,"batnu","analyst"));
        set.add(new Employee1(3,"batnu","ba"));
        System.out.println(set);

    }
}