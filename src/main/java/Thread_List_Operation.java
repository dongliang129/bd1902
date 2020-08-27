import java.util.*;
public class Thread_List_Operation {
    //假设有这bai么一个队列du
    static List list = new LinkedList();
    public static void main(String[] args) {
        Thread t;
        t = new Thread(new T1());
        t.start();
        t = new Thread(new T2());
        t.start();
    }
}
//线程zhiT1,用来给list添加新元素
class T1 implements Runnable{
    void getElemt(Object o){
        Thread_List_Operation.list.add(o);
        System.out.println(Thread_List_Operation.list.size());
        System.out.println(Thread.currentThread().getName() + "为队列添加了一个元素");
    }
    @Override
    public void run() {
        for (int i = 0; ; i++) {
            getElemt(new Integer(1));
        }
    }
}
//线程T2,用来给list添加新元素
class T2 implements Runnable{
    void getElemt(Object o){
        Thread_List_Operation.list.add(o);
        System.out.println(Thread_List_Operation.list.size());
        System.out.println(Thread.currentThread().getName() + "为队列添加了一个元素");
    }
    @Override
    public void run() {
        for (int i = 0; ; i++) {
            getElemt(new Integer(1));
        }
    }
}