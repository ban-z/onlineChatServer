package Database;


import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.sql.*;

/*
* 完成数据库的连接功能
* */
public class DBTest {

    private static Connection connection = null;
    public static void main(String[] args) {

        String uri = "jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC";
        String name = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(uri, name, password);

            /*
            * 测试数据库语句，以及联通性
            * */
            /*Statement sql = connection.createStatement();
            String mQuery = "SELECT password from User where account =" + 12345678;
            ResultSet result = sql.executeQuery(mQuery);
            if (result.next()){
                String pass = result.getString(1);
                if (pass.equals("1234567890")){
                    System.out.println("密码正确：" + pass);
                }else {
                    System.out.println("密码错误: " + pass);
                }
            }else {
                System.out.println("数据库中不存在此用户!");
            }*/

            String User;
            Statement sql = DBTest.connection.createStatement();
            String allUser = "SELECT * from User";
            ResultSet result = sql.executeQuery(allUser);
            while (result.next()){
                User = result.getString(1);
                System.out.println("User: " + User);
            }

            /*
            * 测试getuser()方法
            * */
            /*Statement sql = DBUTest.connection.createStatement();
            String mQuery = "SELECT * from User where account = " + 88888888;
            ResultSet result = sql.executeQuery(mQuery);
            if (result.next()){
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
            }else {
                System.out.println("没有该用户信息!");
            }*/

            /*Statement sql = connection.createStatement();
            String update = "UPDATE User set state = " + 0 + " where account = " + 88888888;
            System.out.println(update);
            sql.executeUpdate(update);*/

            /*
            * 测试添加用户到数据库中
            * */
            /*Statement msql = connection.createStatement();
            String addUser  ="insert into User values(" + 88888888 +"," + 88888888 + "," + 1 + ")";
            msql.execute(addUser);*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库操作异常!");
        }
    }
}
