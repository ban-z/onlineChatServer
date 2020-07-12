package Database;
import com.example.onlinechat.Data.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
* 在连接数据库之后，为满足相应需求完成与数据库的交互
*                   实现登录管理，注册管理，相应列表获取和用户状态管理等功能
* */
public class DBUser {

    /*
    * 对输入参数做判断
    * 如果密码符合，则返回确认信息
    * 如果密码不符合或者账号信息直接不存在，则返回错误信息
    * */
    public boolean login(int acount, String password) {
        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String mQuery = "SELECT password from User where account =" + acount;
            ResultSet result = sql.executeQuery(mQuery);
            if (result.next()){
                String pass = result.getString(1);
                if (pass.equals(password)){
                    return true;
                }
            }else {
                System.out.println("数据库中没有此用户");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(User user) {
        int account = user.getAcount();

        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String mQuery = "SELECT * from User where account =" + account;
            ResultSet result = sql.executeQuery(mQuery);
            if (!result.next()){
                String addUser  ="insert into User values(" + account +"," + user.getPassword() + "," + 1 + ")";
                sql.execute(addUser);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changeState(int account, int state) {
        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String update = "UPDATE User set state= " + state + " where account = " + account;
            sql.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUser(int account) {
        String UserSrt = null;
        String UserName;
        String Userpassword;
        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String mQuery = "SELECT * from User where account = " + account;
            ResultSet result = sql.executeQuery(mQuery);
            if (result.next()){
                UserName = result.getString(1);
                Userpassword = result.getString(2);
                UserSrt = UserName +"," + Userpassword;
            }else {
                System.out.println("没有该用户信息!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserSrt;
    }

    public ArrayList<Integer> getAllUseraccount(){
        ArrayList<Integer> userAccountList = new ArrayList<Integer>();
        int index = 0;
        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String allQuery = "SELECT account from User";
            ResultSet result = sql.executeQuery(allQuery);
            while (result.next()){
                userAccountList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccountList;
    }

    public String getFriendList() {
        int User;
        String friendList = null;
        try {
            Statement sql = SingleDBUtil.getConnectoion().createStatement();
            String allUser = "SELECT * from User";
            ResultSet result = sql.executeQuery(allUser);
            while (result.next()){
                if (friendList == null){
                    friendList = result.getInt(1) + ",";
                }
                User = result.getInt(1);
                /*System.out.println("User: " + User);*/
                friendList += User + ",";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendList;
    }
}
