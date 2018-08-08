package creational.singletonpattern;

import java.sql.Connection;
import java.sql.DriverManager;

public enum EnumSingleton {

    INSTANCE;
    //private Connection connection;
    private creational.singletonpattern.Connection connection;
    private EnumSingleton(){
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


}