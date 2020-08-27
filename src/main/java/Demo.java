import org.junit.Test;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo {

    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
@Test
    public synchronized void onInit() throws Exception {
    queue.offer("1");
    queue.offer("3");
    queue.offer("2");
    int j =0;
    LinkedBlockingQueue<String> linkedBlockingQueue = queue;

       Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (String string : linkedBlockingQueue) {
                        synchronized (Demo.class) {
                            if(!linkedBlockingQueue.contains(string)){
                                continue;
                            }
                            System.out.println(string);
//                            System.out.println(string+"   "+Thread.currentThread().getName());
//                            queue.offer(""+new Random().nextInt(10000));
//                            System.out.println("大小： "+queue.size());
                            test();
                            linkedBlockingQueue.remove(string);
                        }

            }
        }

    };

       for(int o=0;o<5;o++){
           Thread thread = new Thread(runnable);
           thread.start();
       }

}
    private void test() {
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
        queue.offer(""+new Random().nextInt(10000));
    }
}