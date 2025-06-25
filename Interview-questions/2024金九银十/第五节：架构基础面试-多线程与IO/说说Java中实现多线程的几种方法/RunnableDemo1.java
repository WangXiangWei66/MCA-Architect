package 说说Java中实现多线程的几种方法;

public class RunnableDemo1 {

    /**
     * 线程的第二种方式
     * 本质是创建Thread对象时候传递了一个Runnable接口实现
     *
     * @param args
     */
    //t1与t2共享一个Runnable实例，适用于多线程协作场景
    public static void main(String[] args) {
        System.out.println("main执行了....");
        //创建一个新的线程  Thread对象
        Runnable r1 = new RunnableTest();
        Thread t1 = new Thread(r1);
        //启动线程
        t1.start();
        Thread t2 = new Thread(r1);
        t2.start();
        System.out.println("main结束了");
    }
}


/*
线程的第二种创建方式
创建一个Runnable接口的实现类
 */

class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("子线程执行了...");
    }
}
