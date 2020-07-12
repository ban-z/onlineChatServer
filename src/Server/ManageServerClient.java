package Server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
* 将客户端连接服务器的线程加入到此类中，
* 动态管理所有在在线客户端，方便对于消息的转发， 用户状态的管理
* 及其他处理功能
* */

/*
* 问题：
*
* HashMap实现动态管理在线用户的功能还未完善
* 具体问题：
*           1.没有实现用户在线的动态管理，离线用户依然存储在map中
*           2.isOnline函数还待实现正确的处理
* */
public class ManageServerClient {

    public static HashMap hashMap = new HashMap<Integer, SingleServer>();

    //添加一个客户端通信线程
    public static void addClientThread(int account, SingleServer singleServer){
        hashMap.put(account, singleServer);
    }

    //得到一个客户端通信线程
    public static SingleServer getClientThread(int i){
        return (SingleServer) hashMap.get(i);
    }

    //返回当前在线人数情况
    public static ArrayList<Integer> getAllOnlineUserId(){
        ArrayList<Integer> list = new ArrayList<Integer>();

        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()){
            list.add((Integer) it.next());
        }
        return list;
    }

    public static boolean isOnline(int a){
        ArrayList<Integer> list = new ArrayList<Integer>();
        /*System.out.println("isOninle check UserAccount: " + a);*/

        if (hashMap.isEmpty()){
            return false;
        }

        if (hashMap.containsKey(a)){
            return true;
        }

       /* *//*
        * 循环遍历整个HashMap寻找对应的key，效率低
        * 直接用包含判断键来比对
        * *//*
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()){
            *//*int account = (int) it.next();*//*
            System.out.println("hashMap online UserAccount: " + it.next());
            if (a == (int) it.next()){
                return true;
            }
        }*/
        return false;
    }

    public static void removeClientThread(int account){
        hashMap.remove(account);
    }

    public static boolean BroadCast(String broad){
        if ("wait true" == "true"){
            //通过HashMap向所有用户发送广播

            return true;
        }
        return false;
    }
}
