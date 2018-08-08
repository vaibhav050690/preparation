import java.util.HashMap;

public class HashMapImmutableKeysDemo {

    class MyKey {
        private int field1;
        private String feild2;

        public int getField1() {
            return field1;
        }

        public void setField1(int field1) {
            this.field1 = field1;
        }

        public String getFeild2() {
            return feild2;
        }

        @Override
        public String toString() {
            return "MyKey{" +
                    "field1=" + field1 +
                    ", feild2='" + feild2 + '\'' +
                    ", field3=" + field3 +
                    '}';
        }

        public void setFeild2(String feild2) {
            this.feild2 = feild2;
        }

        public double getField3() {
            return field3;
        }

        public void setField3(double field3) {
            this.field3 = field3;
        }

        public MyKey(int field1, String feild2, double field3) {
            this.field1 = field1;
            this.feild2 = feild2;

            this.field3 = field3;
        }

        private double field3;


    }

    public static void main(String[] args) {
        HashMapImmutableKeysDemo obj = new HashMapImmutableKeysDemo();
        HashMap<MyKey,String> map = new HashMap<>();
        HashMapImmutableKeysDemo.MyKey key1 = obj.new MyKey(1,"a",1.00);
        HashMapImmutableKeysDemo.MyKey key2 = obj.new MyKey(2,"b",2.00);
        HashMapImmutableKeysDemo.MyKey key3 = obj.new MyKey(3,"c",3.00);
        HashMapImmutableKeysDemo.MyKey key4 = obj.new MyKey(4,"d",4.00);
        map.put(key1,"a");
        map.put(key2,"b");
        map.put(key3,"c");
        map.put(key4,"d");
        System.out.println(map.get(key1));
        key1.feild2 = "e";
        key1.field1=101;
        key1.field3 = 1234.00;
        System.out.println(map.get(key1));


    }


}