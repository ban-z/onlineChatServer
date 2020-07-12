package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* 单例模式实现Connection的获取，为链接数据库操作的DBUser提供连接对象
*
* 与饿汉式单例模式同理
* */
public class SingleDBUtil {

    private static String uri = "jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC";
    private static String name = "root";
    private static String password = "";
    private static Connection connection;

    /*private  SingleDBUtil(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(uri, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(uri, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*private static Connection SetConneciton(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(uri, name, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/

    public static Connection getConnectoion(){
        /*return SetConneciton();*/
        return connection;
    }
}
