package 如何停止一个正在运行的线程;

import java.util.Date;
//通过协作式机制安全地终止一个正在运行地线程
public class FunDemo02 {
    /**
     * 设计一个线程；运行十秒后被终止（掌握线程的终止方法）
     *
     * @param args
     */

    public static void main(String[] args) throws Exception {
        MyRunnable02 runnable = new MyRunnable02();
        new Thread(runnable).start();
        Thread.sleep(10000);//主线程休眠十秒
        runnable.flag = false;  //使用volatile boolean 类型的标志为(flag)控制线程循环
        System.out.println("main、end ....");
    }
}

class MyRunnable02 implements Runnable {
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(1000);
                System.out.println(new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "执行完成");
    }
}
