package 说说Java中实现多线程的几种方法;

public class ThreadMemo02 {

    /*
    线程的第一种实现方式
        通过创建Thread类的子类来实现
        @param args
     */

    public static void main(String[] args) {  //JAVA程序的入口点，JVM会自动调用该方法
        System.out.println("main方法执行了1...");
        //JAVA中的线程  本质上就是一个Thread对象
        Thread t1 = new ThreadTest01();  //创建自定义的线程类的实例
        //启动一下新的线程
        //start方法并不能直接开启一个线程，真正开启线程的是CPU
        //当CPU空闲或者分配到此任务的时候就会创建一个新的线程，然后执行run方法中的代码
        t1.start();
        //t1.start()线程不能够启动多次，IllegalThreadStateException
        //如果要创建多个线程，就创建多个Thread对象即可
        Thread t2 = new ThreadTest01();
        t2.start();
        //t1.run()这个是显示的调用的Thread的run方法，并没有开启新的线程
        for (int i = 0; i < 100; i++) {
            System.out.println("main方法的循环...." + i);
        }
        System.out.println("main方法执行结束了3...");
    }
}

class ThreadTest01 extends Thread {
    @Override  //重写了Run方法
    public void run() {
        System.out.println("我们的第一个线程执行了2...");
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程:" + i);
        }
    }
}

