package creational.singletonpattern;

import java.sql.Connection;
import java.sql.DriverManager;

public class StaticInnerClassSingleton {

    //private Connection connection;
    private creational.singletonpattern.Connection connection;

    private StaticInnerClassSingleton(){
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "database_name";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "username";
        String password = "password";
        this.connection = new creational.singletonpattern.Connection(url,dbName,driver,userName,password);
        /*try {
            Class.forName(driver).newInstance();
            this.connection = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }*/
    }


    public creational.singletonpattern.Connection getConnection(){
        return connection;
    }

    /*
    When the singleton class is loaded, inner class is not loaded and hence doesn’t create object when
    loading the class.
    Inner class is created only when getInstance() method is called. So it may seem like eager initialization
    but it is lazy initialization.
    This is the most widely used approach as it doesn’t use synchronization.
    */
    private static class Singleton{
        static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }


    public static StaticInnerClassSingleton getInstance(){
        return Singleton.instance;
    }
}