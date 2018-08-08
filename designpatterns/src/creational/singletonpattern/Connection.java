package creational.singletonpattern;

//simulating jdbc connection class for testing
public class Connection {

    private String url;
    private String dbName;

    @Override
    public String toString() {
        return "Connection{" +
                "url='" + url + '\'' +
                ", dbName='" + dbName + '\'' +
                ", driver='" + driver + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Connection(String url, String dbName, String driver, String userName, String password) {
        this.url = url;
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    private String driver;
    private String userName;
    private String password;


}