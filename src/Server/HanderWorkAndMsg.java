package Server;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import Database.DBUser;

import java.io.IOException;
import java.io.ObjectOutputStream;

/*
* 动态管理消息类
* 完成对消息的转发， 好友列表的返回（请求端）
* 还可根据消息扩充对不同通信要求的消息处理
* */
public class HanderWorkAndMsg {
    static DBUser dbUser = new DBUser();

    public static void sendMsg(Message message){
        SingleServer singleServer = ManageServerClient.getClientThread(message.getReceiver());
        try {
            //取得接收者的通信线程
            ObjectOutputStream OOS = new ObjectOutputStream(singleServer.Client.getOutputStream());
            //向接收者发送消息
            OOS.writeObject(message);
            OOS.flush();
            System.out.println(message.getSender() +" Client: -=-=-=-=-=-=>>>" + message.getReceiver() + "::::" + message.getContent());
            OOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendFriendList(Message message){
        try {
            //操作数据库，返回好友列表
            String res = dbUser.getFriendList();
            SingleServer singleServer = ManageServerClient.getClientThread(message.getSender());
            ObjectOutputStream OOS = new ObjectOutputStream(singleServer.Client.getOutputStream());
            Message msgToClient = new Message();
            msgToClient.setType(MessageType.RET_ALL_FRIENDS);
            message.setContent(res);
            OOS.writeObject(message);
            OOS.flush();
            OOS.close();

            System.out.println(message.getSender() + ",请求好友列表信息：" + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
