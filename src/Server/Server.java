package Server;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import com.example.onlinechat.Data.User;
import Database.DBUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/*
* 完成服务端初始化，及监听客户端连接，若有连接到socket，则创建IO流，User，Message对象
* 当验证通过（注册完成并登录，或正常登录）后，返回相应信息包，并启动一个线程，专门用于和此客户端的通信
* 把每一个和客户端通信的线程放入动态管理在线User的管理类（HashMap）中
* */
public class Server {
    public Server(){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(6767);
            System.out.println("服务器启动！" + new Date());
            /*
            * 开启用户状态管理的线程
            * */
            while (true){
                Socket Client = serverSocket.accept();

                System.out.println("Accpet Client:" + Client.toString());

                //若有则开始处理消息

                /*
                * 一定要保证OOS在OIS之前实例化
                * */
                /*System.out.println("接收成功，开始获取ClientIO流");*/
                ObjectOutputStream OOS = new ObjectOutputStream(Client.getOutputStream());
                ObjectInputStream OIS = new ObjectInputStream(Client.getInputStream());
                User user = (User) OIS.readObject();
                OIS.close();
                /*System.out.println("接受Client的User：" + user.toString());*/

                Message message = new Message();

                if (user.getOperation().equals("login")){
                    DBUser dbUser = new DBUser();
                    int account = user.getAcount();
                    boolean loginResult = dbUser.login(account, user.getPassword());
                    if (loginResult){
                        System.out.println("Client: 【" +  account + "】, 上线成功");

                        /*测试isOnlie函数*//*
                        System.out.println("Client: " + account +" 在线判断: "+ManageServerClient.isOnline(account));
                        *//*测试isOnlie函数*/

                        //更改数据库用户状态
                        dbUser.changeState(account, 1);
                        //返回请求信息
                        String userStr = dbUser.getUser(account);
                        message.setType(MessageType.SUCCESS);
                        message.setContent(userStr);
                        OOS.writeObject(message);


                        /*解决Connection reset异常测试--------------------------------------------------------------------------------------------*/
                        OOS.flush();
                        OOS.close();
                        /*解决Connection reset异常测试--------------------------------------------------------------------------------------------*/

                        //为登录的用户单独开启线程，进行通信
                        SingleServer singleServer = new SingleServer(account, Client);

                        ManageServerClient.addClientThread(user.getAcount(), singleServer);
                        /*System.out.println("Client: " + account +" 在线判断: "+ManageServerClient.isOnline(account));*/

                        /*
                        * 测试HashMap
                        * *//*
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SingleServer singleServer2 = ManageServerClient.getClientThread(1234567890);
                                while (true){
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("HashMap: " + ManageServerClient.getAllOnlineUserId());
                                    if (!ManageServerClient.hashMap.containsKey(1234567890)){
                                        System.out.println("Client 1234567890: 已经离线了！");
                                    }
                                }
                            }
                        }).start();
                        *//*
                         * 测试HashMap
                         * */

                        //启动线程开始通信
                        singleServer.start();
                        new ListenIsOnline().start();
                    }else {
                        //登录失败，拒绝登录
                        message.setType(MessageType.FAIL);
                        OOS.writeObject(message);
                        OOS.flush();
                        OOS.close();
                    }
                }else if (user.getOperation().equals("register")){
                    DBUser dbUser = new DBUser();
                    if (dbUser.register(user)){
                        message.setType(MessageType.SUCCESS);
                        OOS.writeObject(message);
                        OOS.flush();
                        OOS.close();
                    }
                }else {
                    //测试其他错误
                    System.out.println("Server正常运行，请检查客户端实现！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
