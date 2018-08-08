package creational.singletonpattern;

public class SingletonClientTest {

    public static void main(String[] args) {
        DoubleCheckedLockingSingleton doubleCheckedLockingSingleton = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton doubleCheckedLockingSingleton1 = DoubleCheckedLockingSingleton.getInstance();
        System.out.println("doubleCheckedLockingSingleton == doubleCheckedLockingSingleton1 :" + (doubleCheckedLockingSingleton == doubleCheckedLockingSingleton1));

        StaticInnerClassSingleton staticInnerClassSingleton = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton staticInnerClassSingleton1 = StaticInnerClassSingleton.getInstance();
        System.out.println("staticInnerClassSingleton == staticInnerClassSingleton1 :" + (staticInnerClassSingleton == staticInnerClassSingleton1));

        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;
        System.out.println("enumSingleton == enumSingleton1 :" + (enumSingleton == enumSingleton1));

        System.out.println(doubleCheckedLockingSingleton.getConnection());
        System.out.println(staticInnerClassSingleton.getConnection());
        System.out.println(enumSingleton.getConnection());

    }
}