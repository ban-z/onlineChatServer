package Client;

import com.example.onlinechat.Data.User;

import java.io.*;
import java.net.Socket;

public class Client {
    private static String SOCKET_IP = "192.168.43.93";
    private static int SOCKET_PORT = 6767;

    public static void main(String[] args) {

        Socket client;
        Receive receive;
        Thread readData;
        User userLogin;
        User userRegister;

        ObjectOutputStream OOS;
        ObjectInputStream OIS = null;

        try {
            client = new Socket(SOCKET_IP, SOCKET_PORT);

            userLogin = new User("login", 1234567890, "1234567890");
            userRegister = new User("register", 123456, "123456");
            /*System.out.println(userLogin.toString());
            System.out.println(client.getInputStream());
            System.out.println(client.getOutputStream());*/

            /*
            * 流的获取出现问题
            *
            * 问题已解决-------》保证OOS在OIS之前完成实例化
            * */
            OOS = new ObjectOutputStream(client.getOutputStream());
            OIS = new ObjectInputStream(client.getInputStream());


            OOS.writeObject(userLogin);
        } catch (IOException e) {
            System.out.println("连接失败，请检查协议地址！");
        }
        /*try {*/
            receive = new Receive();
            readData = new Thread(receive);
            receive.setObjectInputStream(OIS);
            readData.start();
        /*} catch (IOException e) {
            System.out.println("失败，请检查ClientIO流！");
        }*/
    }
}
