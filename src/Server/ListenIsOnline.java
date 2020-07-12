package Server;

import Database.DBUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListenIsOnline extends Thread {

    DBUser dbUser;
    int now_index = 0;
    int all_index = 0;
    ArrayList<Integer> allUserList;
    ListenIsOnline(){
        dbUser = new DBUser();
        allUserList = dbUser.getAllUseraccount();
        all_index = allUserList.size();
    }

    @Override
    public void run() {
        while (true){
            /*if (ManageServerClient.getAllOnlineUserId() == null){
                while (true){
                    dbUser.changeState(allUserList.get(now_index), 0);
                    now_index++;
                    if (now_index == all_index - 1){
                        break;
                    }
                }
            }*/

            /*System.out.println(allUserList.get(now_index));*/
            if (!(ManageServerClient.isOnline(allUserList.get(now_index)))){
                dbUser.changeState(allUserList.get(now_index), 0);
                /*System.out.println("Client 【" + allUserList.get(now_index) + "】: 现在离线 + " + new Date());*/
            }
            if (now_index == all_index - 1){
                now_index = 0;
            }
            now_index++;
        }
    }
}
