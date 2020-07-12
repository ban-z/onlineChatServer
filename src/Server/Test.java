package Server;

import Database.DBUser;

public class Test {
    public static void main(String[] args) {
        /*DBUser dbUser = new DBUser();
        boolean result = dbUser.login(1234567890, "1234567890");
        if (result){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }*/
        System.out.println(ManageServerClient.getAllOnlineUserId());
    }
}
