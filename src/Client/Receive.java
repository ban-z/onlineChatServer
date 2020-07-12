package Client;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Receive implements Runnable {

    ObjectInputStream OIS;
    Message message;

    @Override
    public void run() {
        try {
            while (true) {
                message = (Message) OIS.readObject();
                String check = message.getType();
                if (check.equals(MessageType.SUCCESS)){
                    System.out.println("登录成功！");
                }else {
                    System.out.println("请检查用户名和密码，重新登录");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public void setDataInputStream(DataInputStream inputStream){
        Din = inputStream;
    }*/
    public void setObjectInputStream(ObjectInputStream inputStream){
        OIS = inputStream;
    }
}
