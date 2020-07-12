package Server;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
* 服务器和客户端单独通信的进程，并根据消息类型的不同
* 交给动态消息管理类去做不同的处理
* */
public class SingleServer extends Thread {

    Socket Client;
    int Account;


    public SingleServer(int account, Socket client) {
        Account = account;
        Client = client;
    }

    @Override
    public void run() {
        while (true){
            ObjectInputStream OIS = null;
            Message message;
            try {
                OIS = new ObjectInputStream(Client.getInputStream());
                message = (Message) OIS.readObject();
                //判断客户端的消息类型，选择处理方式
                if (message.getType().equals(MessageType.COM_MSG)){
                    //当接受到的为普通包
                    HanderWorkAndMsg.sendMsg(message);
                } else if (message.getType().equals(MessageType.RET_ALL_FRIENDS)) {
                    HanderWorkAndMsg.sendFriendList(message);
                }
                OIS.close();
            } catch (IOException e) {
                try {
                    ManageServerClient.removeClientThread(Account);
                    Client.close();
                    OIS.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
